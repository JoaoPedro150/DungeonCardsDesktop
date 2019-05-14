package com.tsi.card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tsi.card.Card.TipoCard;
import com.tsi.card.CardDeAtaque.TipoArma;
import com.tsi.chars.Heroi;
import com.tsi.chars.Inimigo;
import com.tsi.chars.InimigoTransformavel;
import com.tsi.item.Arma;
import com.tsi.item.Pocao;
import com.tsi.ui.Audio;
import com.tsi.ui.Sprite;

@SuppressWarnings("unused")
public class Cards {
	private static HashMap<String, Card> cards = new HashMap<>();
	private static ArrayList<String> cardsPorNome = new ArrayList<>();

	public static void carregarCards() {
		Card card;
		Inimigo inimigo;
		InimigoTransformavel inimgoTransformavel;
		Pocao pocao;
		Arma arma;

        try {
        	for (Object tipos : (JSONArray) new JSONParser().parse(new FileReader("cards.json"))) {
        		for (Object obj : (JSONArray)((JSONObject)tipos).get("lista")) {
        			card = parseCard((JSONObject) obj);

        			switch (((JSONObject)tipos).get("tipo").toString()) {
					case "inimigos":
						card.setAudio(new Audio(((JSONObject)tipos).get("audio").toString()));

						if (((JSONObject) obj).get("transformavel") != null) {
							inimigo = new Inimigo(parseCard((JSONObject)((JSONObject) obj).get("transformavel")));
							inimigo.setAudio(card.getAudio());

							inimgoTransformavel = new InimigoTransformavel(card, inimigo);
							cards.put(inimgoTransformavel.getNome(), inimgoTransformavel);
						}
						else
							inimigo = new Inimigo(card);

						cards.put(inimigo.getNome(), inimigo);
						cardsPorNome.add(card.getNome());

						break;
					case "pocoes":
						pocao = new Pocao(card);
						pocao.setAudio(new Audio(((JSONObject) obj).get("audio").toString()));
						pocao.setTipoCard(TipoCard.valueOf(((JSONObject) obj).get("tipo").toString()));
						cards.put(pocao.getNome(), pocao);
						cardsPorNome.add(pocao.getNome());
						break;
					case "armas":
	        			card.setAudio(new Audio(((JSONObject) obj).get("audio").toString()));
	    				arma = new Arma(card);
	    				arma.setTipoArma(TipoArma.valueOf(((JSONObject) obj).get("tipo").toString()));
	    				cards.put(arma.getNome(), arma);
						cardsPorNome.add(arma.getNome());
						break;
					}
        		}
            }

        }
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	public static Card getRandomCard() {
		Card card = cards.get(cardsPorNome.get(new Random().nextInt(cardsPorNome.size())));
		card.setValor(new Random().nextInt(30) + 1);
		return card.clone();
	}

	private static Card parseCard(JSONObject jsonCard) {
		Card card = new Card();

		card.setNome(jsonCard.get("nome").toString());
		card.setSprite(new Sprite(jsonCard.get("sprite").toString()));

		return card;
	}
}
