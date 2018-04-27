# Vykreslování rostlin pomocí L-systému

Program je interaktivní a slouží k demonstraci kreslení růstu rostlin pomocí L-systému. Program nemá žádné vstupní parametry, vyžaduje od uživatele jen používání tlačítek v grafickém rozhraní. 

### Popis programu

Po spuštění programu se otevře uživateli okno s grafickým rozhraním, kde se nachází pozadí pro kreslení, a několik tlačítek. Nachází se zde tlačítka pro zvolení typu rostliny, tlačítko pro vykreslování vybraného typu rostliny, tlačítko pro resetování typu rostliny a pozadí. Nakonec se zde nachází tlačítko pro ukončení programu. Pomocí tlačítek si uživatel může vykreslovat jednotlivé rostliny po jednotlivých iteracích. Program běží do doby, než ho uživatel sám vypne.

### Funkcionalita

Program nejprve volá metodu pro vytvoření okna. Následně se do okna přidají objekty tříd, které reprezentují kreslení, pozadí a tlačítka. Tyto objekty jsou rozděleny do dvou tříd, které dědí ze třídy JPanel, jsou to třídy DrawPanel a ControlPanel. Při vytvoření objektu, jenž reprezentuje tlačítka, dojde k volání konstruktoru dané třídy a vytvoření jednotlivých tlačítek. Při vytvoření objektu reprezentujícího kreslení a pozadí, vytvoří konstruktor dané třídy bílé pozadí. Oba zmíněné konstruktory přijímají jako vstupní parametr objekt třídy Plants, který reprezentuje parametry a pravidla pro jednotlivé rostliny, jako vlastnosti objektu. Po stisknutí jakéhokoliv tlačítka s typem rostliny, se zavolá metoda ze třídy Plants a nastaví objektu příslušné parametry pro vytvoření tohoto typu rostliny. Jednotlivá tlačítka pro výběr typu rostliny se tímto zakážou a povolí se tlačítko pro generování. Stisknutím tlačítka „Generovat“ se zavolá kreslící metoda ze třídy  DrawPanel. Tato metoda vytvoří grafický objekt pro kreslení a resetuje veškeré vlastnosti objektu třídy DrawPanel. Následně metoda funguje na principu želví grafiky, kde želva (virtuální kurzor) plní příkazy podle jednotlivých symbolů, které se nacházejí ve Stringu dané vlastnosti objektu Plants. Takto želva nakreslí jednu iteraci dané rostliny. Déle je zavolána metoda třídy Plants, která podle pravidla vygeneruje z již použitého Stringu další znaky. Následně je délka pro kreslení linií zkrácena o půlku. Toto tlačítko je možné používat, dokud je počítač schopný vykreslovat jednotlivé linie. Stisknutím tlačítka „Začni znovu“ se překreslí pozadí, resetují se všechny vlastnosti objektu třídy Plants a umožní opět používat tlačítka typů rostlin. Tlačítko pro generování se zakáže. Poslední tlačítko „Konec“ vypíná program. 

### Algoritmus

Mým záměrem bylo implementovat L-systém, který funguje na principu vykreslování pomocí želví grafiky. L-systém je obdoba gramatiky, která generuje z počátečního symbolu podle pravidel další symboly, kterými se želva řídí při kreslení.

### Problematická místa

Největší problém byla neznalost faktu, že symboly „[“ a „]“ mají ukládat a načítat veškeré proměnné, týkající se pozice a rotace želvy. Ze začátku jsem ukládal jen její pozici nikoliv úhel natočení.

Dominik Mazur 
