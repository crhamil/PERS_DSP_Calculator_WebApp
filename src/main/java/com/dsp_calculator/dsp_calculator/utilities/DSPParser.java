package com.dsp_calculator.dsp_calculator.utilities;

import static com.dsp_calculator.dsp_calculator.utilities.Utilities.getElementSafe;
import static com.dsp_calculator.dsp_calculator.utilities.Utilities.getElementsSafe;
import static com.dsp_calculator.dsp_calculator.utilities.Utilities.getFuelTypeSafe;
import static com.dsp_calculator.dsp_calculator.utilities.Utilities.getTextContentSafe;
import static com.dsp_calculator.dsp_calculator.utilities.Utilities.parseFloatSafe;
import static com.dsp_calculator.dsp_calculator.utilities.Utilities.parseIntSafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;
import com.dsp_calculator.dsp_calculator.models.components.Fuel;
import com.dsp_calculator.dsp_calculator.models.components.FuelType;
import com.dsp_calculator.dsp_calculator.models.components.buildings.ConveyorBelt;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Extractor;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Factory;
import com.dsp_calculator.dsp_calculator.models.components.buildings.PowerPlant;
import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;
import com.dsp_calculator.strategies.factory.ComponentFactory;
import com.dsp_calculator.strategies.factory.ExtractorFactory;
import com.dsp_calculator.strategies.factory.PowerPlantFactory;

@Component
public class DSPParser implements ApplicationRunner {
	private final String FILENAME = "data.xml";
	private Game game = Game.getInstance();
	
	@Override
	public void run(ApplicationArguments args) throws RecipeException {
		System.out.println("....... Initialisation");
		setItems();
	    setRecipes();
	    System.out.println("....... Données initialisées");
	    System.out.println("....... " + game.getRecipes().size() + " recettes");
	}
	
	// TODO: faire en sorte que les catégories soient récupérées directement à partir du fichier
	private void setBuilding(Element el, String category, String id, String name, int row, int stack) throws FuelTypeException {
		Element el_factory = getElementSafe(el, "factory"); /* tag factory */
		Element el_mining = getElementSafe(el, "mining"); /* tag mining */
		Element el_belt = getElementSafe(el, "belt"); /* tag belt */
		Element el_mining_speed = getElementSafe(el_mining, "speed"); /* speed à l'intérieur d'un tag mining */
		Element el_factory_speed = getElementSafe(el_factory, "speed"); /* speed à l'intérieur d'un tag factory */
		Element el_factory_type = getElementSafe(el_factory, "type"); /* type à l'intérieur d'un tag factory */
		Element el_factory_fuel_type = getElementSafe(el_factory, "category"); /* pour les centrales à carburant, type de carburant à l'intérieur d'un tag factory */
		Element el_factory_value = getElementSafe(el_factory, "value"); /* value à l'intérieur d'un tag factory */
		Element el_usage = getElementSafe(el_factory, "usage"); /* usage à l'intérieur d'un tag factory */
		Element el_drain = getElementSafe(el_factory, "drain"); /* drain à l'intérieur d'un tag factory */
		Element el_modules = getElementSafe(el_factory, "modules"); /* modules à l'intérieur d'un tag factory */
		/* Extracteurs */
		/* Spécificité : <mining><speed></speed></mining> */
		if (el_mining_speed != null) {
			String speed = el_mining_speed.getTextContent();
			String usage = getTextContentSafe(el_usage);
			String drain = getTextContentSafe(el_drain);
			Extractor extractor = ExtractorFactory.getExtractor(id, name, row, stack, parseFloatSafe(drain), parseIntSafe(usage), parseIntSafe(speed));
			game.addGameItem(id, extractor);
			game.addBuilding(id, extractor);
			game.addExtractor(id, extractor);
		}
		else if (el_factory_type != null) {
			String factory_type = getTextContentSafe(el_factory_type);
			String factory_fuel_type_string = getTextContentSafe(el_factory_fuel_type);
			FuelType fuel_type = getFuelTypeSafe(factory_fuel_type_string);
			float factory_speed = parseFloatSafe(getTextContentSafe(el_factory_speed));
			int usage = parseIntSafe(getTextContentSafe(el_usage));
			int drain = parseIntSafe(getTextContentSafe(el_drain));
			int value = parseIntSafe(getTextContentSafe(el_factory_value));
			int modules = parseIntSafe(getTextContentSafe(el_modules));
			
			/* Centrales */
			/* Spécificité : <type>burner</type> ou <type>electric-production</type> */
			if (factory_type.equals("burner") || factory_type.equals("electric-production")) {
				PowerPlant powerPlant = PowerPlantFactory.getPowerPlant(factory_type, id, name, row, stack, factory_speed, value, fuel_type);
				game.addGameItem(id, powerPlant);
				game.addBuilding(id, powerPlant);
				game.addPowerPlant(id, powerPlant);
			}
			
			/* Usines classiques */
			else if (factory_type.equals("electric")) {
				if (factory_speed < 0) factory_speed = 1; // Vitesse par défaut : 1
				Factory factory = new Factory(id, name, row, stack, factory_speed, usage, drain, modules);
				game.addGameItem(id, factory);
				game.addBuilding(id, factory);
				game.addFactory(id, factory);
			}
		}
		/* Tapis roulant */
		/* Spécificité : <belt></belt> */
		else if (el_belt != null) {
			Element el_belt_speed = getElementSafe(el_belt, "speed");
			String belt_speed = getTextContentSafe(el_belt_speed);
			ConveyorBelt conveyorBelt = new ConveyorBelt(id, name, row, stack, parseIntSafe(belt_speed));
			game.addGameItem(id, conveyorBelt);
			game.addBuilding(id, conveyorBelt);
		}
		/* Infrastructures simples */
		else {
			Building building = new Building(id, name, row, stack);
			game.addGameItem(id, building);
			game.addBuilding(id, building);
		}
	}
	
	private void setComponent(Element el, String category, String id, String name, int row, int stack) throws FuelTypeException {
		Element el_fuel = getElementSafe(el, "fuel"); /* tag fuel */
		Element el_fuelType = getElementSafe(el_fuel, "category"); /* category à l'intérieur d'un tag fuel */
		Element el_fuelValue = getElementSafe(el_fuel, "value"); /* value à l'intérieur d'un tag fuel */
		NodeList nl_miners = getElementsSafe(el, "minedby"); /* tag minedby */
		ArrayList<Building> miners = new ArrayList<>();
		for (int i = 0; i < nl_miners.getLength(); i++) { /* récupération des id des mineurs de ressource et de leur objet dans le jeu */
			String miner_id = getTextContentSafe((Element) nl_miners.item(i));
		    miners.add(game.getBuildings().get(miner_id));
		}
		String fuelTypeString = getTextContentSafe(el_fuelType);
		FuelType fuelType = getFuelTypeSafe(fuelTypeString);
		int fuelValue = parseIntSafe(getTextContentSafe(el_fuelValue));
		
		DSPComponent component = ComponentFactory.getComponent(category, id, name, row, stack, fuelType, fuelValue, miners);
		game.addGameItem(id, component);
		game.addComponent(id, component);
		if (component instanceof Fuel) game.addFuel(id, (Fuel) component);
	}
	
	public void setItems() {
		for (String c : game.getCategories()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(new ClassPathResource(FILENAME).getFile());
	
				NodeList list = doc.getElementsByTagName("items");

				for (int temp = 0; temp < list.getLength(); temp++) {
					Node node = list.item(temp);
					String name = null, category = null, id = null;
					int stack = 0, row = 0;
	
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) node;
						category = getElementSafe(el, "category").getTextContent();
						id = getElementSafe(el, "id").getTextContent();
						name = getElementSafe(el, "name").getTextContent();
						row = parseIntSafe(getElementSafe(el, "row").getTextContent());
						stack = parseIntSafe(getElementSafe(el, "stack").getTextContent());
						
						if (category.equals(c) && c.equals("buildings")) {
							setBuilding(el, category, id, name, row, stack);
						} else if (category.equals(c) && c.equals("resource")) {
							setComponent(el, category, id, name, row, stack);
						} else if (category.equals(c) && c.equals("components")) {
							setComponent(el, category, id, name, row, stack);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setRecipes() throws RecipeException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ClassPathResource(FILENAME).getFile());
			
			NodeList list = doc.getElementsByTagName("recipes");

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) node;
					String id = getElementSafe(el, "id").getTextContent(); /* id */
					String name = getElementSafe(el, "name").getTextContent(); /* nom */
					float time = parseFloatSafe(getTextContentSafe(getElementSafe(el, "time"))); /* temps de production */
					
					Element el_input = getElementSafe(el, "in"); /* tag in */
					Element el_output = getElementSafe(el, "out"); /* tag out */
					Element el_producers = getElementSafe(el, "producers"); /* tag producers */
					
					/* Producteurs (en réalité, un seul par recette) */
					String producers_id = getTextContentSafe(el_producers);
					Factory producer = (Factory) game.getGameItems().get(producers_id);
					ArrayList<Factory> producers = new ArrayList<>();
					producers.add(producer);
					
					/* Ingrédients (en entrée) */
					HashMap<DSPComponent, Float> input = new HashMap<>();
					NodeList nl_input = getElementsSafe(el_input, "*");
					for (int i = 0; i < nl_input.getLength(); i++) {
						String in_id = nl_input.item(i).getNodeName();
						DSPComponent c = game.getGameItems().get(in_id);
						if (c == null) throw new RecipeException("Erreur : composant de recette invalide");
						float qty = parseFloatSafe(getTextContentSafe(getElementSafe(el_input, in_id)));
				        input.put(c, qty);
					}
					
					/* Produits (en sortie) */
					HashMap<DSPComponent, Float> output = new HashMap<>();
					if (el_output != null) {
						NodeList nl_output = getElementsSafe(el_output, "*");
						for (int i = 0; i < nl_output.getLength(); i++) {
							String out_id = nl_output.item(i).getNodeName();
							DSPComponent c = game.getGameItems().get(out_id);
							if (c == null) throw new RecipeException("Erreur : composant de recette invalide");
							float qty = parseFloatSafe(getTextContentSafe(getElementSafe(el_output, out_id)));
					        output.put(c, qty);
						}
					} else {
						DSPComponent c = game.getGameItems().get(id);
						if (c == null) throw new RecipeException("Erreur : composant de recette invalide");
						output.put(c, (float) 1);
					}
					
					Recipe recipe = new Recipe(id, name, time, producers, input, output);
					game.addRecipe(id, recipe);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
}
