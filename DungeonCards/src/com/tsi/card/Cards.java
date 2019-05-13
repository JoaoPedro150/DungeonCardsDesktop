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
import com.tsi.chars.Heroi;
import com.tsi.chars.Inimigo;
import com.tsi.item.Pocao;
import com.tsi.ui.Audio;
import com.tsi.ui.Sprite;

@SuppressWarnings("unused")
public class Cards {
	private HashMap<String, Card> cards = new HashMap<>();
	private ArrayList<String> cardsPorNome = new ArrayList<>();

	public Cards() {
		Card card;
		Pocao pocao;

        try {
        	for (Object tipos : (JSONArray) new JSONParser().parse(new FileReader("cards.json"))) {
        		for (Object obj : (JSONArray)((JSONObject)tipos).get("lista")) {
        			card = parseCard((JSONObject) obj);

        			switch (((JSONObject)tipos).get("tipo").toString()) {
					case "inimigos":
						card.setAudio(new Audio(((JSONObject)tipos).get("audio").toString()));
						cards.put(card.getNome(), new Inimigo(card));
						cardsPorNome.add(card.getNome());
						break;
					case "pocoes":
						pocao = new Pocao(card);
						pocao.setAudio(new Audio(((JSONObject) obj).get("audio").toString()));
						pocao.setTipoCard(TipoCard.valueOf(((JSONObject) obj).get("tipo").toString()));
						cards.put(pocao.getNome(), pocao);
						cardsPorNome.add(pocao.getNome());
						break;
					}

        			System.out.println(((JSONObject) obj).get("nome").toString());
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

	public Card getRandomCard() {
		return getCard((cardsPorNome.get(new Random().nextInt(cardsPorNome.size()))));
	}

	public Card getCard(String nome) {
		Card card = cards.get(nome);

		if (card != null) {
			try {
				Method method = this.getClass().getDeclaredMethod("get" + nome.replace(" ", ""), Card.class);
				method.setAccessible(true);

				card = (Card) method.invoke(this, card);

			} catch (NoSuchMethodException e) {

			}
			catch (IllegalAccessException | IllegalArgumentException |
					InvocationTargetException | SecurityException e) {

				e.printStackTrace();
			}
		}

		card.setValor(new Random().nextInt(15) + 1);
		return card.clone();
	}

	private Card parseCard(JSONObject jsonCard) {
		Card card = new Card();

		card.setNome(jsonCard.get("nome").toString());
		card.setSprite(new Sprite(jsonCard.get("sprite").toString()));

		return card;
	}

	private Inimigo getZumbiMascarado(Card card) {
    	card.setInformacao("Transforma-se em Zumbi.");

        return new Inimigo(card) {
    		@Override
        	public Inimigo clone() {
        		return new Inimigo(super.clone()) {
        			@Override
                    public Card interagir(Heroi heroi) {
                        Card card = super.interagir(heroi);
                        return (card == null) ? getCard("Zumbi") : card;
                    }
        		};
        	}
        };
    }
}
