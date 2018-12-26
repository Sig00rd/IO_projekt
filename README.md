# SPIS TREŚCI
[1. Lista wymagań](#lista_wymagan)




#lista_wymagan
LISTA WYMAGAŃ

Wymagania funkcjonalne:
#########################################################################
I.Użytkownik
------------------------------------------------------------------------------------------------------------------------
1.Zapewnienie rejestracji i logowania
------------------------------------------------------------------------------------------------------------------------
2.Wybranie ulubionych sportów i określenie poziomu w każdym
------------------------------------------------------------------------------------------------------------------------
3.Obsługa zaproszenia do gry

Użytkownik będąc w panelu widoku gry ma możliwość kliknięcia przycisku “Zaproś znajomych”, wyszukania innych użytkowników i zaproszenia ich na daną grę. Wtedy system wysyła zaproszonym stosowne powiadomienie.
------------------------------------------------------------------------------------------------------------------------
4.Możliwość zgłaszania użytkowników łamiących zasady

#########################################################################
II.Szukający
------------------------------------------------------------------------------------------------------------------------
1.Szukanie gry 

Użytkownik wybiera jedną interesującą go dyscyplinę(np.piłka nożna), następnie określa parametry gry takie jak: poziom gry(checkbox), obszar przeszukiwania (użytkownik podaje punkt na mapie i promień, można ręcznie dodawać i usuwać uwzględnione boiska),przedział godzinowy gry, datę, maksymalny koszt oraz zaznacza czy chce szukać gier w których musiałby wpisać się na rezerwę. Użytkownik klika opcję “Szukaj”.
Zostają wyświetlone gry, które spełniają sprecyzowane parametry. Użytkownik może kliknąć dowolną z nich aby wyświetlić pełne szczegóły wraz z opcją zapisu na grę.

Istnieje możliwość zapamiętywania parametrów szukania (dyscyplina, poziom gry, obszar przeszukiwania, maksymalny koszt)


Uwaga: Różne sporty mogą mieć różne dodatkowe parametry, które należy uwzględnić, dla przykładu piłka nożna może mieć dodatkowe pole ‘nawierzchnia’, gdzie użytkownik precyzuje czy chce grać na hali czy może na sztucznej trawie.

Uwaga nr 2: W różnych sportach są różne pozycje na boisku, dla przykładu w piłce nożnej istotne może być rozróżnienie na bramkarza i zawodnika w polu, ponieważ stosunkowo często zdarza się że bramkarz poszukiwany jest z większym priorytetem.
------------------------------------------------------------------------------------------------------------------------
2.Szukanie potencjalnej gry

Użytkownik wybiera jedną interesującą go dyscyplinę(np.piłka nożna), następnie określa parametry gry takie jak: poziom gry(checkbox), obszar dostępności(użytkownik podaje punkt na mapie i promień, można ręcznie dodawać i usuwać uwzględnione boiska),przedział godzinowy gry w których jest dostępny, datę, maksymalny koszt oraz zaznacza czy chce szukać gier w których by musiał się wpisać na rezerwę. Użytkownik klika opcję “Znajdź mi grę”. Opcja ta różni się tym od “Szukaj gry”, że tutaj użytkownik szuka gier które jeszcze nie powstały. Gdy gra spełniająca jego wymagania zostanie utworzona, zostanie on natychmiast powiadomiony.

Reszta jak w punkcie 1.



Uwaga: Użytkownik w każdej chwili może zrezygnować z bycia dostępnym, wtedy przechodzi do zakładki “Moje gry/Przeszukiwane” i klika “Zrezygnuj”.
------------------------------------------------------------------------------------------------------------------------
3.Obsługa zapisów do gier

Będąc w widoku szczegółowym danej gry, użytkownik może kliknąć opcję “Zapisz mnie”.
Jeżeli gra posiada komplet graczy lub użytkownik nie łapie się na priorytetowy nabór, zamiast opcji “Zapisz mnie”, wyświetlony zostaje stosunkowy komunikat oraz opcja “Zapisz mnie na rezerwę”(rezerwa działa na zasadzie kolejki). W momencie gdy zwolni się miejsce, lub gra zacznie akceptować graczy o mniejszym priorytecie, system automatycznie zapisuje tylu graczy z rezerwy, ilu jest potrzebnych aby wypełnić braki kadrowe oraz wysyła im powiadomienie o tym fakcie. Zapisany staje się pełnoprawnym uczestnikiem spotkania, ma obowiązek się na nim pojawić.Jeżeli zapisana zostaje osoba z rezerwy, która została zapisana automatycznie na grę, jednak było to po godzinie ostatecznej deklaracji, nie daje podstaw do jej zgłoszenia w przypadku nie stawienia się na tej grze.
Jeżeli użytkownik zapisał się do gry, a jednocześnie używał funkcjonalności przedstawionej w punkcie 2, system pyta użytkownika czy dalej chce poszukiwać kolejnych potencjalnych gier zgodnie z jego preferencjami, jeśli nie - system usuwa jego deklarację o byciu dostępnym.

Użytkownik może w każdej chwili wypisać się z gry do której się zapisał, jednak musi liczyć się z konsekwencjami. Jeżeli był na rezerwie może w każdej chwili wypisać się bez konsekwencji. Jeżeli był w pierwszym składzie i zrobił to przed godziną ostatecznej deklaracji, dzieje się to bez konsekwencji. Jeżeli natomiast będzie już po godzinie ostatecznej deklaracji, może on dalej wypisać się z gry (a nawet powinien jeżeli wie że nie da rady się na niej pojawić), lecz daje wtedy podstawy organizatorowi spotkania do zgłoszenia tego występku administratorom co wiąże się z minusowymi punktami reputacji. 





#########################################################################
III.Organizator
------------------------------------------------------------------------------------------------------------------------
1.Utworzenie gry

Użytkownik wybiera boisko, godzinę, koszt, kolor koszulek, godzinę ostatecznej deklaracji.
Te rzeczy nie mogą ulec późniejszej zmianie i zostają do końca takie jakie są. Następnie ma możliwość ustalenia pewnego priorytetu akceptacji.

Przykład priorytetu: Pewna gra w piłkę nożną zaplanowana na 20:00, docelowo ma się odbywać na poziomie określonym jako “dobry”.  Brakuje 5 zawodników w pole. Dodatkowo brakuje jednego bramkarza do kompletu. Organizator chce żeby gra odbyła się na przewidzianym poziomie, jednak przede wszystkim chce żeby w ogóle się odbyła. Wybiera on więc: poziom “do przyjęcia” osobno dla gracza w polu i na bramce, poziom priorytetowy osobno dla gracza w polu i na bramce, a także godzinę zaniknięcia priorytetu. Oczywiście dla innych sportów nie musi być to bramkarz tylko coś innego.

Przed godziną zaniknięcia priorytetu użytkownicy, którzy nie łapią się tylko na pozycję “do przyjęcia” i tak mogą tę grę wyświetlać, a nawet na nią zapisywać. Zapisują się jednak wtedy na rezerwę.

Wybrany zostaje też tryb gry (publiczny,prywatny), a organizator może już od razu w panelu tworzenia gry zaprosić znajomych.



Uwaga: Organizator może też wybrać opcję bez priorytetu.

------------------------------------------------------------------------------------------------------------------------
2.Modyfikacja gry

Użytkownik w panelu własnej utworzonej gry ma możliwość zmieniania różnych charakterystyk poszukiwanych graczy(poziom,pozycja), a także modyfikację priorytetu oraz trybu gry. 

------------------------------------------------------------------------------------------------------------------------
3.Usuwanie gry

Użytkownik w panelu własnej utworzonej gry ma możliwość usunięcia jej z systemu. W takiej sytuacji wszyscy zapisani na nią gracze (zarówno główny skład jak i rezerwa) zostają o tym fakcie powiadomieni. Istnieje możliwość dodania komentarza do tej operacji, który wyświetli się wraz z powiadomieniem.

------------------------------------------------------------------------------------------------------------------------
4.Wysłanie komunikatu członkom spotkania

Użytkownik w panelu własnej utworzonej gry ma możliwość wysłania komunikatu do wszystkich członków spotkania. W takiej sytuacji wszyscy zapisani na nią gracze (zarówno główny skład jak i rezerwa) otrzymują ten komunikat.

------------------------------------------------------------------------------------------------------------------------
5.Weryfikacja zawodników pod kątem pojawienia się na spotkaniu

Po spotkaniu, organizator ma możliwość oceny czy wszyscy zawodnicy którzy zgłosili się na spotkanie pojawili się na nim.
#########################################################################

IV.Dodający obiekty
------------------------------------------------------------------------------------------------------------------------
1.Dodanie obiektu

Użytkownik wybiera opcję “Dodaj obiekt”. Wpisuję nazwę obiektu, adres oraz rodzaj obiektu(hala,orlik). Następnie klika przycisk “Dalej”, pokazują mu się wszystkie dane już bez pól edycji, oraz mapa z markerem wyznaczonym na podstawie adresu (ma możliwość powrotu do edycji). Jeżeli wszystko się zgadza, klika opcję “Dodaj obiekt”. Zostaje poinformowany że obiekt czeka na zatwierdzenie administratorów, administratorzy dostają powiadomienie o nowej prośbie dodania obiektu.

#########################################################################
V.Administrator
------------------------------------------------------------------------------------------------------------------------
1.Zatwierdzenie obiektu

Po kliknięciu w powiadomienie o nowej prośbie dodania obiektu pojawią się mu okienko takie samo jak dodającemu obiekt po kliknięciu przycisku dalej. Ma możliwość kliknięcia zatwierdź lub odrzuć, oraz dodania komentarza do tej akcji. Obiekt zatwierdzony ląduje w bazie danych.
------------------------------------------------------------------------------------------------------------------------
2.Dodanie obiektu

To samo co w IV.1, z tym że nie musi czekać na zatwierdzenie
------------------------------------------------------------------------------------------------------------------------

