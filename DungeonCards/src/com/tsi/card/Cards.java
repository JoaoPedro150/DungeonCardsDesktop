package com.tsi.card;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tsi.card.Card.TipoCard;
import com.tsi.card.CardDeAtaque.TipoArma;
import com.tsi.chars.Inimigo;
import com.tsi.chars.InimigoTransformavel;
import com.tsi.item.Arma;
import com.tsi.item.Pocao;
import com.tsi.itemespecial.Bau;
import com.tsi.ui.Audio;
import com.tsi.ui.Sprite;

public class Cards {
	private static HashMap<String, Card> cards = new HashMap<>();
	private static ArrayList<String> cardsPorNome = new ArrayList<>();
	private static ArrayList<String> cardsRuim = new ArrayList<>();
	private static ArrayList<String> cardsBom = new ArrayList<>();

	public static void carregarCards() {
		Card card;
		Inimigo inimigo;
		Pocao pocao;
		Arma arma;
		Bau bau;

        try {
        	for (Object tipos : (JSONArray) new JSONParser().parse(new FileReader("cards.json"))) {
        		for (Object obj : (JSONArray)((JSONObject)tipos).get("lista")) {
        			card = parseCard((JSONObject) obj);

        			switch (((JSONObject)tipos).get("tipo").toString()) {
					case "inimigo":
						card.setAudio(new Audio(((JSONObject)tipos).get("audio").toString()));

						if (((JSONObject) obj).get("transformavel") != null) {
							inimigo = new Inimigo(parseCard((JSONObject)((JSONObject) obj).get("transformavel")));
							inimigo.setAudio(card.getAudio());
							cards.put(inimigo.getNome(), inimigo);

							inimigo = new InimigoTransformavel(card, inimigo);
						}
						else
							inimigo = new Inimigo(card);

						addCard(inimigo);

						break;
					case "pocao":
						pocao = new Pocao(card);
						pocao.setAudio(new Audio(((JSONObject) obj).get("audio").toString()));
						pocao.setTipoCard(TipoCard.valueOf(((JSONObject) obj).get("tipo").toString()));
						pocao.setInformacao(((JSONObject) obj).get("info").toString());
						addCard(pocao);
						break;
					case "arma":
	    				arma = new Arma(card);
	    				arma.setAudio(new Audio(((JSONObject) obj).get("audio").toString()));
	    				arma.setTipoArma(TipoArma.valueOf(((JSONObject) obj).get("tipo").toString()));
	    				arma.setAlcance(Integer.valueOf(((JSONObject) obj).get("alcance").toString()));
	    				addCard(arma);
						break;
					case "bau":
	    				bau = new Bau(card);
	    				bau.setTipoCard(TipoCard.valueOf(((JSONObject) obj).get("tipo").toString()));
	    				addCard(bau);
						break;
					}
        		}
            }

        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
	}

	private static <T extends Card> void addCard(T card) {
		cards.put(card.getNome(), card);
		cardsPorNome.add(card.getNome());

		if (card.getTipoCard().equals(TipoCard.BOM)) {
			cardsBom.add(card.getNome());
		}
		else
			cardsRuim.add(card.getNome());
	}

	public static Card getRandomCard() {
		return getRandomCard(null);
	}
	public static Card getRandomCard(TipoCard tipo) {
		Card card;

		if (tipo == null)
			card = cards.get(cardsPorNome.get(new Random().nextInt(cardsPorNome.size())));
		else if (tipo.equals(TipoCard.BOM))
			card = cards.get(cardsBom.get(new Random().nextInt(cardsBom.size())));
		else
			card = cards.get(cardsRuim.get(new Random().nextInt(cardsRuim.size())));

		card.setValor(new Random().nextInt(15) + 1);
		return card.clone();
	}

	private static Card parseCard(JSONObject jsonCard) {
		Card card = Card.getInstance();

		card.setNome(jsonCard.get("nome").toString());
		card.setSprite(new Sprite(jsonCard.get("sprite").toString()));

		return card;
	}
}
