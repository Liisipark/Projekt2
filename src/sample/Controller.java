package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Controller {
    @FXML
    private TextArea info;

    @FXML
    private  Button katkesta;

    @FXML
    private  Button edasi;

    @FXML
    private  Button jätka;

    @FXML
    private  Button osta;

    @FXML
    private ChoiceBox filmid;

    //lisatakse gilmid loetelusse
    ObservableList<String> filmide_loend = FXCollections
            .observableArrayList("Jääag 2", "Terminaator", "Voonakeste vaikimine", "Karu süda");

    @FXML
    private TextField mitu_piletit;

    // sündmused, mis toovad osade kaupa nähtavale uut infot ja valikuid
    @FXML
    private void initialize() {

        //peidame vaatest üleliigse info
        filmid.setVisible(false);
        jätka.setVisible(false);
        mitu_piletit.setVisible(false);
        osta.setVisible(false);

        //esimene juhis
        info.setText("Tere tulemast kinno Smaragt! Kinokavaga tutvumiseks ja pileti ostmiseks vajuta nupule 'Edasi'");

        //"edasi" nupp toob kavas olevate filmide valiku ja edasise juhendi
        edasi.setOnMouseClicked(event -> {
            info.setText("Siin on toodud meie kavas olevad filmid. Palun vali sobiv film ja vajuta nuppu 'jätka'");
            edasi.setVisible(false);
            filmid.setVisible(true);
            filmid.setItems(filmide_loend);
            jätka.setVisible(true);

        });

        // jätka nupu vajutusel ilmub piletite ostu kastike ja osta nupp
        jätka.setOnMouseClicked(event -> {
            info.setText("Valisid filmiks " + filmid.getSelectionModel().getSelectedItem().toString()
                    + ". Palun sisesta, mitut piletit soovid osta ning vajuta 'enter'. " +
                    "Korraga on võimalik osta kuni 10 piletit");
            filmid.setVisible(false);
            jätka.setVisible(false);
            mitu_piletit.setVisible(true);
            osta.setVisible(true);

        });

        // mitu piletit soovitakse osta + kontrollitakse sisendit
        mitu_piletit.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (!mitu_piletit.getText().equals("")) {
                    kontroll();
                }
            }
        });

        // ostu nupu vajutuse järel tänatakse, ja peidetakse üleliigsed nupud
        osta.setOnMouseClicked(event -> {
            info.setText("Edasi jälgige kaarditerminali juhiseid! Täname, et otsustasite meie kino valida!");
            mitu_piletit.setVisible(false);
            katkesta.setVisible(false);
            osta.setVisible(false);
        });


        //kasutaja vajutab 'katkesta' nupule
        katkesta.setOnMouseClicked(event -> {
            info.setText("Head päeva!");
            edasi.setVisible(false);
            katkesta.setVisible(false);
            mitu_piletit.setVisible(false);
        });
    }

    // kontrollitakse sisendit - kas on arv vahemikus 1-10 ja kas on üldse arv (erind)
    private void kontroll() {
        if (!mitu_piletit.getText().equals("")) {
            int arv;
            try{
                arv = Integer.parseInt(mitu_piletit.getText());
                if (arv > 0 && arv < 11) {
                    info.setText("Soovite filmile " + filmid.getSelectionModel().getSelectedItem().toString()
                            + " osta " + arv + " piletit. Ostu maksumus on " + arv * 5 + " eurot."
                            + " Tasumiseks vajuta 'osta' nuppu");
                    mitu_piletit.setText("");
                } else
                    info.setText("Filmile saab osta korraga 1-10 piletit, palun sisesta korrektne täisarv");
            }catch (NumberFormatException e){
                info.setText("See ei olnud vist number, palun proovige uuesti");
                arv = 0;
            }
        }
    }

}




