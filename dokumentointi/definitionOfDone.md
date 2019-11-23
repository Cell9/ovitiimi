# User storyn Definition of done

- User storylla on hyväksymiskriteerit [Sprint backlogilla](https://docs.google.com/spreadsheets/d/1iyOaUwoDaijE3uMPPIeSPnU4EpCxYCItDZS4TlaIsyY/edit#gid=355581877)
- hyväksymiskriteerit on kirjattu Cucumberin featureiksi
- README:stä linkki hyväksymäkriteerit määritteleviin tiedostoihin
- koodin JUnit testien kattavuus tulee olla vähintään 75% (rivikattavuus)
- Asiakas pääsee näkemään sen hetkisen koodin ja testien tilanteen CI-palvelusta
- luokat, metodit ja muuttujat on nimetty järkevästi
- ohjelmointi toteutettu järkevää arkkitehtuuria soveltaen
- käytetty yhtenäistä koodityyliä

### Definition donen täyttymisen tarkistamisen tulee olla pitkälle automatisoitu

- hyväksymiskriteerien täyttymisen tarkistus Cucumberilla
- rivikattavuuden tarkistus Jacocolla
- ohjelma kääntyy kokonaisuudessaan CircleCI-palvelimella
- koodityylin tarkistus checkstylellä (käytettävä koodityyli on määritetty checkstylen toiminnan määrittelevään konfiguraatiotiedostoon)
