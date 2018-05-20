### Vykreslování rostlin pomocí L-systému

Program je interaktivní a slouží k demonstraci kreslení růstu rostlin pomocí L-systému. Program nemá žádné vstupní parametry, vyžaduje od uživatele jen používání tlačítek v grafickém rozhraní. 

## Popis programu
Po spuštění programu se otevře uživateli okno s grafickým rozhraním, kde se nachází pozadí pro kreslení, a několik tlačítek. Nachází se zde tlačítko pro zvolení typu rostliny, tlačítko pro vykreslování vybraného typu rostliny, tlačítko pro zastavení výpočtu a resetování pozadí, tlačítko pro výběr počtu iterací a nakonec se zde nachází tlačítko pro ukončení programu. Pomocí tlačítek si uživatel může vykreslovat jednotlivé rostliny s vybraným počtem iterací. Program běží do doby, než ho uživatel sám vypne.

## Funkcionalita

Program nejprve volá metodu pro vytvoření grafického rozhraní. Následně se do vytvořeného okna přidají objekty tříd, které reprezentují kreslení a tlačítka. Tyto objekty jsou rozděleny do tří tříd, které dědí ze třídy JPanel, jsou to třídy DrawPanel, ControlPanel a ControlPanelUP. Při vytvoření objektu ControlPanel, jenž reprezentuje tlačítka, dojde k volání konstruktoru dané třídy a vytvoření jednotlivých tlačítek v pravé části okna. Při vytvoření objektu ControlPanelUP dochází k vytvoření tlačítek v horní oblasti, které reprezentují typ rostliny a počet iterací. Tyto tlačítka jsou zobrazena ve formě comboboxů. Při vytvoření objektu reprezentujícího kreslení a pozadí, vytvoří konstruktor dané třídy bílé pozadí. Po vybrání typu rostliny se nastaví tlačítko s iteracemi na doporučený počet iterací pro danou rostlinu. Dále se uloží do vlastností objektu DrawPanel číslo vybraného typu rostliny. Po stisknutí tlačítka „Generate“ se spustí nové vlákno, které v pozadí provede výpočty linií pro vykreslení rostliny. Nejprve se vytvoří pomocí parametrického konstruktoru a čísla vybraného typu rostliny objekt třídy Plants. V konstruktoru se podle čísla vybraného typu zavolá metoda, která naplní vlastnosti objektu pro tvorbu vybraného typu rostliny. Následně je volána metoda pro generování posloupnosti znaků z axiomu podle nastavených pravidel a počtu iterací. Dále je volána metoda DrawPanelu, která z vygenerovaných znaků vytvoří linie podle významu jednotlivých znaků. Tyto linie jsou uloženy do listu a je volána metoda, která tyto linie vykreslí. Zatímco se provádí výše popsaný výpočet, je možné jakkoliv manipulovat s grafickým rozhraním. Pokud je nastaven příliš velký počet iterací a výpočet trvá dlouho, je možné stisknout tlačítko "Stop calculating process and clear out the background", které přeruší výpočet a vyčistí pozadí. Tlačítko „Close“ ukončuje program.

## Algoritmus

Mým záměrem bylo implementovat L-systém, který funguje na principu vykreslování pomocí želví grafiky. L-systém je obdoba gramatiky, která generuje z počátečního symbolu podle pravidel další symboly, kterými se želva řídí při kreslení.

## Problematická místa

Největší problém byla neznalost faktu, že symboly „[“ a „]“ mají ukládat a načítat veškeré proměnné, týkající se pozice a rotace želvy. Ze začátku jsem ukládal jen její pozici nikoliv úhel natočení. Dalším problémem bylo vytvoření vlákna, přesouvání proměnných do metod, kde jsou potřeba, z třídy do třídy a podobně.


Dominik Mazur 

