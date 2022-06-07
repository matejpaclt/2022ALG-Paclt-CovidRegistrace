# CovidRegistrace
## Zadání práce
### Popis problému
Program CovidRegistrace bude mít za účel provádět registraci pacientů na testování a očkování proti onemocnění COVID-19. Vstupem bude textový soubor obsahující seznam míst, kde se lze očkovat nebo testovat. V programu půjde také zjistit kdy bude muset pacient na další očkování a na jak dlouho mu vydrží očkování nebo test. Pacient zadá do systému:
* povinně
  * jméno
  * příjmení
  * rodné číslo
  * pohlaví
  * email
  * uživatelské jméno a heslo
* Dále si pacient vybere zda:
  * se chce očkovat
  * testovat
  * nebo jen zkontrolovat svoje údaje
* Pokud se chce pacient očkovat nebo testovat: 
  * Napíše město a místo, ve kterém by se chtěl nechat očkovat
  * Vybere si datum a čas

Pro každého pacienta bude vygenerována pozvánka v pdf k očkování či testování s údaji o místu, typu očkování a datumu, který si vybrali.
## Řešení
### Funkční specifikace
Aplikace he tvořena přihlašovacím menu a hlavním menu. Uživatel zadává výběr především pomocí čísla. Uživatel se buď zaregistruje nebo přihlásí. Dále si uživatel vybere zda chce vygenerovat pozvánku na testování nebo očkování nebo jen zkontrolovat svoje přihlašovací údaje. V generaci pozvánky si uživatel vybere zařízení z nabídky a zadá za kolik dní  a v kolik by si návštěvu přál.
* Přihlašovací menu
   *  `1` Zaregistrovat
      *  `String` uživatelské jméno 
      *  `String` heslo
      *  `String` potvrzení hesla
      *  `String` jméno
      *  `String` příjmení
      *  `M`/`F` pohlaví
      *  `String` email
      *  `číslo` rodné číslo
   *  `2` Přihlásit
      *  `String` uživatelské jméno 
      *  `String` heslo
   *  `0` Konec  
* Hlavní menu
   *  `1` Očkování
   *  `2` Testování
   *  `3` Zkotrolovat profil
   *  `0` Konec
* Očkování/Testování 
   *  `číslo ze seznamu` Místo očkování/testování 
   *  `číslo 1-31` Den očkování/testování 
   *  `číslo 6-17` Hodina očkování/testování 
### Popis struktury vstupních a výstupních souborů
* program čte PlacesVac.csv a PlacesTest.csv v \data, ve kterých jsou vypsané očkovací a testovací stanice
   * ty obsahují pořadové číslo, název a adresu
* Výstupem je pdf,txt soubor a výpis na konzoli s pozvánkou na očkování/testování s datumem, časem, místem, oslovením a dnešním datem

### Class diagram

##Testování

## Popis fungování externí knihovny
*  iText
*  Vytvořena v roce 2000
*  iText je knihovna Java PDF, díky které lze převádět a manipulovat s dokumenty PDF.
*  JAR file



    
