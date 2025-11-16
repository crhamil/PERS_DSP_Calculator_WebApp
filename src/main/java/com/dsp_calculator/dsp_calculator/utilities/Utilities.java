package com.dsp_calculator.dsp_calculator.utilities;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dsp_calculator.dsp_calculator.models.components.FuelType;

public class Utilities {
	
	/**
	 * Récupère le premier élément enfant correspondant à un tag donné sans lever d'exception en cas d'erreur.
	 *
	 * @param parent L'élément parent dans lequel chercher.
	 * @param tag Le nom de la balise à rechercher.
	 * @return Le premier {@link Element} correspondant au tag, ou {@code null} si aucun élément n'es présent ou une erreur survient.
	 */
	public static Element getElementSafe(Element parent, String tag) {
	    try {
	        Node node = parent.getElementsByTagName(tag).item(0);
	        return (node instanceof Element) ? (Element) node : null;
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	/**
	 * Version sans levée d'exception de {@link Element#getElementsByTagName(String)}.
	 * Récupère tous les éléments enfants correspondant à un tag donné sans lever d'exception en cas d'erreur.
	 *
	 * @param parent L'élément parent dans lequel chercher.
	 * @param tag Le nom de la balise à rechercher.
	 * @return Une {@link NodeList} correspondant aux éléments trouvés, ou {@code null} si aucun élément n'es présent ou une erreur survient.
	 */
	public static NodeList getElementsSafe(Element parent, String tag) {
	    try {
	        NodeList nodes = parent.getElementsByTagName(tag);
	        return (nodes instanceof NodeList) ? nodes : null;
	    } catch (Exception e) {
	        return null;
	    }
	}

	/**
	 * Version sans levée d'exception de {@link Node#getTextContent()}.
	 * Récupère le contenu d'un tag donné sans lever d'exception en cas d'erreur.
	 *
	 * @param el L'élément duquel on souhaite extraire le contenu.
	 * @return Une {@link NodeList} correspondant aux éléments trouvés, ou {@code null} si aucun élément n'es présent ou une erreur survient.
	 */
	public static String getTextContentSafe(Element el) {
	    return (el != null) ? el.getTextContent() : null;
	}

	/**
	 * Version sans levée d'exception de {@link Integer#parseInt(String)}.
	 *
	 * @param value La chaîne à convertir en nombre entier.
	 * @return Le nombre entier parsé si la chaîne est valide, ou {@code -1} en cas d'erreur.
	 */
	public static int parseIntSafe(String value) {
	    try {
	        return Integer.parseInt(value);
	    } catch (Exception e) {
	        return -1;
	    }
	}
	
	/**
	 * Version sans levée d'exception de {@link Float#parseFloat(String)}.
	 *
	 * @param value La chaîne à convertir en nombre réel.
	 * @return Le nombre réel parsé si la chaîne est valide, ou {@code -1} en cas d'erreur.
	 */
	public static float parseFloatSafe(String value) {
	    try {
	        return Float.parseFloat(value);
	    } catch (Exception e) {
	        return -1;
	    }
	}
	
	/**
	 * Instancie un {@link FuelType} à partir d'une chaîne de caractères.
	 * Agit comme une factory simplifiée pour l'énumération {@link FuelType},
	 * en retournant l'instance correspondante à une chaîne connue.
	 *
	 * @param str La chaîne représentant un type de carburant (par ex. {@code "chemical"}, {@code "antimatter"}, {@code "nuclear"}).
	 * @return L'instance de {@link FuelType} correspondante, ou {@code null} si la chaîne est invalide.
	 * @throws FuelTypeException str est non-vide, mais ne correspond pas à un type de carburant connu.
	 */
	public static FuelType getFuelTypeSafe(String str) throws FuelTypeException {
		if (str == null) return null;
		switch(str) {
		case "chemical": return FuelType.CHEMICAL;
		case "antimatter": return FuelType.ANTIMATTER;
		case "nuclear": return FuelType.NUCLEAR;
		default: throw new FuelTypeException("Type de carburant inconnu : " + str);
		}
	}
	
}
