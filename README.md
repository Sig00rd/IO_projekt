# SPIS TREŚCI:
[1. Architektura systemu](#architektura-systemu)
<br>
[2. Przypadki użycia](#przypadki-użycia)
<br> 
[3. Lista wymagań](#lista-wymagań)
<br>
[4. Stos technologicny](#stos-technologiczny)
<br>
[5. Projekt testów](#projekt-testów)
<br>
[6. Lista narzędzi](#lista-narzędzi)
<br>
[7. Opis interfejsów](#opis-interfejsów)

# ARCHITEKTURA SYSTEMU
<img src="https://images.tinypic.pl/i/00974/t5dnp3l8q4fu.png">

# PRZYPADKI UŻYCIA
<img src="https://raw.githubusercontent.com/bartlomiejzachariasz/IO_project/master/use_cases.png?token=ArA1VHIGytvLByIJcdzL8oGqQYKlp2eqks5b_sw1wA%3D%3D">

# LISTA WYMAGAŃ

## Wymagania funkcjonalne:

### I.Użytkownik

#### 1.Zapewnienie rejestracji i logowania

#### 2.Obsługa zaproszenia do gry

Użytkownik będąc w panelu widoku gry ma możliwość kliknięcia przycisku “Zaproś znajomych”, wyszukania innych użytkowników i zaproszenia ich na daną grę. Wtedy system wysyła zaproszonym stosowne powiadomienie. Odbiorca po kliknięciu w powiadomienie może przejść do panelu gry i zapisać się na nią.

#### 3.Dodanie obiektu

Użytkownik wybiera opcję “Dodaj obiekt”. Wpisuje nazwę obiektu, adres oraz rodzaj obiektu(hala,orlik). Jeżeli wszystko się zgadza, klika opcję “Dodaj obiekt”. Zostaje poinformowany że obiekt został dodany.

### II.Szukający

#### 1.Szukanie gry 

Użytkownik wybiera jedną interesującą go dyscyplinę(np.piłka nożna), następnie może określić parametry gry takie jak: poziom gry(checkbox), <b>obszar przeszukiwania</b> (użytkownik podaje punkt na mapie i promień, można ręcznie dodawać i usuwać uwzględnione boiska), <b>przedział godzinowy gry</b>, datę, maksymalny koszt oraz minimalną ilość potrzebnych graczy. Użytkownik klika opcję “Szukaj”. Zostają wyświetlone gry, które spełniają sprecyzowane parametry. Użytkownik może kliknąć dowolną z nich aby wyświetlić pełne szczegóły wraz z opcją zapisu na grę.

Uwaga: Różne sporty mogą mieć różne dodatkowe parametry, które należy uwzględnić, dla przykładu piłka nożna może mieć dodatkowe pole ‘nawierzchnia’, gdzie użytkownik precyzuje czy chce grać na hali czy może na sztucznej trawie.

#### 2.Obsługa zapisów do gier

Będąc w widoku szczegółowym danej gry, użytkownik może kliknąć opcję “Zapisz mnie”.
Jeżeli gra posiada komplet graczy lub użytkownik nie łapie się na priorytetowy nabór, zamiast opcji “Zapisz mnie”, wyświetlony zostaje stosunkowy komunikat oraz opcja “Zapisz mnie na rezerwę”(rezerwa działa na zasadzie kolejki). W momencie gdy zwolni się miejsce, lub gra zacznie akceptować graczy o mniejszym priorytecie, system <b>automatycznie zapisuje</b> tylu graczy z rezerwy, ilu jest potrzebnych aby wypełnić braki kadrowe. Zapisany staje się pełnoprawnym uczestnikiem spotkania, ma obowiązek się na nim pojawić.

Użytkownik może w każdej chwili wypisać się z gry do której się zapisał, jednak musi liczyć się z konsekwencjami, że jeżeli zrobi to za późno, osłabi swoją reputację na portalu.

### III.Organizator

#### 1.Utworzenie gry

Użytkownik wybiera typ dyscypliny, boisko, godzinę, poziom gry, koszt, ilość potrzebnych graczy.Te rzeczy nie mogą ulec późniejszej zmianie i zostają do końca takie jakie są. Następnie ma możliwość ustalenia pewnego priorytetu akceptacji.

Przy wybraniu opcji z priorytetem, organizator może sprecyzować konkretne role boiskowe(np. dla piłki nożnej będą to: bramkarz, obrońca, pomocnik, napastnik), które potrzebuje do swojej gry. Wybiera też ilość każdej z poszczególnych ról (domyślnie 0). Następnie, może wyznaczyć godzinę zaniknięcia priorytetu. Po tej godzinie, już wszyscy użytkownicy niezależnie od roli mają równy priorytet zapisu na grę.

Przed godziną zaniknięcia priorytetu użytkownicy, którzy nie łapią się tylko na pozycję “do przyjęcia” i tak mogą tę grę wyświetlać, a nawet na nią zapisywać. Zapisują się jednak wtedy na rezerwę.


Uwaga: Organizator może też wybrać opcję bez priorytetu.


#### 2.Zarządzanie  grą

Użytkownik w panelu własnej utworzonej gry ma możliwość usunięcia jej z systemu. W takiej sytuacji wszyscy zapisani na nią gracze (zarówno główny skład jak i rezerwa) zostają o tym fakcie powiadomieni. Istnieje możliwość dodania komentarza do tej operacji, który wyświetli się wraz z powiadomieniem.

#### 3.Wysłanie komunikatu członkom spotkania

Użytkownik w panelu własnej utworzonej gry ma możliwość wysłania komunikatu do wszystkich członków spotkania. Można tam informować o rzeczach typu kolor koszulek, posiadanie znaczników itp.  W takiej sytuacji wszyscy zapisani na nią gracze (zarówno główny skład jak i rezerwa) otrzymują ten komunikat.


## Wymagania niefunkcjonalne:
<il>
<li> Maksymalny czas oczekiwania na odpowiedź serwera - 4 sekundy </li>
<li> Maksymalna ilość klientów naraz - 100 </li>
<li> Zapewnione podstawowe bezpieczeństwo </li>
<li> Ilość obsługiwanych transakcji na sekundę - 50 </li>
<li> Skalowalność na inne dziedziny sportowe </li>
</il>

# STOS TECHNOLOGICZNY 
<img src="https://raw.githubusercontent.com/bartlomiejzachariasz/IO_project/master/stack.png?token=ArA1VBRu2QEumY91ZK3-wn1bpJpj71MWks5b_xCTwA%3D%3D">

# PROJEKT TESTÓW
W projekcie testów wydzielono 4 etapy testowania w trakcie cyklu powstawania aplikacji: fazy testów jednostkowych, integracyjnych, systemowych oraz akceptacyjnych.

## Testy jednostkowe.
W tej fazie automatycznym testom będą poddawane wyizolowane jednostki kodu należące przede wszystkim do logiki aplikacji. Kod Javy i Springa będzie testowany za pomocą JUnit i Mockito, zaś do Javascriptowego użyty zostanie Jest.
## Testy integracyjne.
Będą sprawdzać komunikację między interfejsami poszczególnych modułów. Plan zakłada testy integracyjne najpierw kontrolera z logiką aplikacji, a następnie dołączanie kolejno modułu odpowiedzialnego za bazę danych, warstwy widoku i interfejsu graficznego, do sprawdzenia integracji całego systemu i wykrycia oraz korekcji błędów na poszczególnych wcześniejszych etapach. Zespół będzie wykorzystywał MockMVC oraz Protractor.
## Testy systemowe.
Na tym etapie zastosowane zostaną testy funkcjonalne i niefunkcjonalne dzięki którym możliwa będzie ocena w jakim stopniu system spełnia postawione mu wymagania funkcjonalne i niefunkcjonalne. Zostanie sprawdzona m.in. wydajność i odporność na przeciążenia systemu. Zespół zakłada używanie Protractora i Selenium, choć jest możliwym, że w tej fazie stosowane będą głównie testy manualne.
## Testy akceptacyjne.
W tej fazie przeprowadzone będą głównie manualne, czarnoskrzynkowe testy całego systemu ukierunkowane na sprawdzenie użyteczności systemu dla jego potencjalnego użytkownika oraz zgodności działania aplikacji z wymaganiami w stopniu satysfakcjonującym osobę która z aplikacji mogłaby korzystać. W tę fazę testów zaangażowane będą osoby które wcześniej nie odpowiadały za testy aby “zasymulować” przeprowadzanie takich testów przez klienta.


Oprócz tego przy wprowadzaniu większych zmian używane będą testy regresyjne.

Pokrycie kodu testami będzie sprawdzane za pomocą Jesta oraz Codecov.

# LISTA NARZĘDZI
W projekcie zostanie użyty system kontroli wersji - <b>git</b>. Repozytorium będzie hostowane na serwisie <b>GitHub</b> co umożliwi m. in. na issue tracking lub to-do list.

# OPIS INTERFEJSÓW

Aplikacja będzie posiadała interfejs webowy oraz mobilny. Użytkownik będzie miał możliwość utworzenia własnego konta oraz w razie potrzeby odzyskania hasła. Po zalogowaniu i wyborze trybu szukania gry, aplikacja wyświetli mapę oraz listę dostępnych gier, spełniających wymagania podane przez użytkownika. W przypadku chęci założenia gry, wyświelony zostanie formularz z danymi niezbędnymi do utworzenia nowej grupy.
