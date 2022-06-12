# CovidRegistrace
## Zadání práce
### Popis problému
Program CovidRegistrace bude mít za účel provádět registraci pacientů na očkování proti onemocnění COVID-19. Vstupem bude textový soubor obsahující seznam míst, kde se lze očkovat. Pacient zadá do systému:
* povinně
  * jméno
  * příjmení
  * rodné číslo
  * pohlaví
  * email
  * uživatelské jméno a heslo
* Dále si pacient vybere zda:
  * se chce očkovat
  * zkontrolovat na které termíny je objednán
  * nebo jen zkontrolovat svoje údaje
* Pokud se chce pacient očkovat nebo testovat: 
  * Vybere si datum
  * Napíše město a místo, ve kterém by se chtěl nechat očkovat
  * Vybere si čas

Pro každého pacienta bude vygenerována pozvánka v pdf k očkování s údaji o místu, typu očkování a datumu, který si vybrali.
## Řešení
### Funkční specifikace
Aplikace je tvořena přihlašovacím menu a hlavním menu. Uživatel zadává výběr především pomocí čísla. Uživatel se buď zaregistruje nebo přihlásí. Dále si uživatel vybere zda chce vygenerovat pozvánku na testování nebo jen zkontrolovat svoje termíny nebo přihlašovací údaje. V generaci pozvánky si uživatel vybere zařízení z nabídky a zadá datum
a čas, kdy by návštěvu přál.
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
   *  `1` Zapsat termín na očkování
       *   `1-30` den
       *   `1-12` měsíc
       *   `číslo` rok
       *   `1-147` místo ze seznamu
       *   `6-17` hodina očkování
   *  `2` Zkontrolovat Vaše termíny
   *  `3` Zkotrolovat profil
   *  `0` Konec
   
### Popis struktury vstupních a výstupních souborů
* program čte PlacesVac.csv v \data, ve kterých jsou vypsané očkovací stanice
   * ty obsahují pořadové číslo, název a adresu
* Výstupem je pdf,txt soubor a výpis na konzoli s pozvánkou na očkování/testování s datumem, časem, místem, oslovením a dnešním datem
* dalším výstupem jsou csv soubory nazvané datumem ve složce dates (např. Dates\12-06-2022.csv) které obsahují počet volných míst v daný datum, na daném místě v danou hodinu
* další výstup je soubor Apointments.csv který obsahuje seznam všech vzniklých objednání na očkování

### Class diagram
![Screenshot - 08_06_2022 , 0_37_50](https://user-images.githubusercontent.com/92588479/172494894-dfa3acae-8bcf-4161-bb78-5f2521b5ac32.png)

## Testování
### 1.test registrace

![Screenshot - 08_06_2022 , 0_42_18](https://user-images.githubusercontent.com/92588479/172496740-3535ca7f-9996-48de-80a0-fe263d30313e.png)

### 2.test login

![Screenshot - 08_06_2022 , 0_44_21](https://user-images.githubusercontent.com/92588479/172495404-22be6857-96aa-428d-a38f-66c4a44f996c.png)

### 3.test očkování

![Screenshot - 08_06_2022 , 0_55_35](https://user-images.githubusercontent.com/92588479/172496619-5f428ff8-ee0e-4214-929d-8d29dc328ecd.png)

### 4.test neúspěšný login

![Screenshot - 08_06_2022 , 0_45_28](https://user-images.githubusercontent.com/92588479/172496625-329dac29-3e25-4fce-8f47-1401248b802a.png)

### 5.test nevalidní vstup v menu

![Screenshot - 08_06_2022 , 0_46_22](https://user-images.githubusercontent.com/92588479/172496630-0f09a569-003d-449d-aba9-e8f91b374174.png)

### 6. test testování

![Screenshot - 08_06_2022 , 1_02_27](https://user-images.githubusercontent.com/92588479/172497286-75812244-d162-40e2-aa5a-937ce0d55485.png)

### 7. test kontrola profilu

![Screenshot - 08_06_2022 , 1_04_29](https://user-images.githubusercontent.com/92588479/172497503-90bae561-c088-41a4-9c83-b340f15a7a08.png)


### 8. test očkování s chybnými vstupy

![Screenshot - 08_06_2022 , 1_07_24](https://user-images.githubusercontent.com/92588479/172497765-d87f248d-019b-4505-8882-cb0b4231cddb.png)

### 9. test ukončení programu
![Screenshot - 08_06_2022 , 1_09_07](https://user-images.githubusercontent.com/92588479/172497919-2fa1c1c3-d6f8-4b44-9cfc-cc75bc143b26.png)

### 10. test chybný vstup registrace

![Screenshot - 08_06_2022 , 1_10_44](https://user-images.githubusercontent.com/92588479/172498038-9c2b7226-1841-4961-a30f-feabcb132176.png)

## Popis fungování externí knihovny
*  iText
*  Vytvořena v roce 2000
*  iText je knihovna Java PDF, díky které lze převádět a manipulovat s dokumenty PDF.
*  JAR file



    
