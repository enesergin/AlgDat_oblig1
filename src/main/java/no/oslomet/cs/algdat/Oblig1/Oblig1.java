package no.oslomet.cs.algdat.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.util.NoSuchElementException;

public class Oblig1 {
    private Oblig1() {}

    ///// Oppgave 1 //////////////////////////////////////
    //Metoden følger instruksene som er gitt i obligen. Den finner største verdi ved å flytte den bakerst.
    public static int maks(int[] a) throws NoSuchElementException {
        if (a.length == 0){ //Dersom vi får innsendt en array med lengde 0, skal vi kaste ut en melding med passende tekst. En tabell med 0 verdier kan ikke ha en største verdi.
            throw new NoSuchElementException("Tabellen kan ikke være tom!");
        }

        for (int i=0; i < a.length-1; i++) //looper gjennom tabellen som har blitt sendt inn.
        {
            if (a[i] > a[i + 1]) //dersom tallet vi er på (a[i]) er større enn tallet foran (a[i+1]) vet vi at den må flyttes forover i tabellen og vi går inn i if-setningen
            {
                int temp = a[i]; //setter en temporary verdi slik at vi ikke mister verdien til a[i] på neste linje
                a[i] = a[i + 1];
                a[i + 1] = temp;
                //Nå har vi byttet plass på de to tallene og går gjennom for-løkken igjen så lenge betingelsen er true, og ender opp med siste tall på slutten av tabellen.
            }
        }
        return a[a.length-1]; //returnerer det siste tallet i tabellen a.
    }

    //Metoden har som et mål å telle antall ombyttinger som blir gjort. Metoden er nesten helt lik metoden maks, men den teller antall ombyttinger via en hjelpevariabel.
    public static int ombyttinger(int[] a) {
        int teller = 0; //definerer teller-variabelen som skal plusses for hver ombytting
        for (int i=0; i < a.length-1; i++)
        {
            if (a[i] > a[i+1])
            {
                teller++;//hver gang vi går inn i if-setningen i løkken, skjer det en ombytting og vi plusser på telleren
                int temp = a[i];
                a[i]= a[i+1];
                a[i+1] = temp;
            }
        }
        return teller; //returnerer teller
    }

    ///// Oppgave 2 //////////////////////////////////////
    //Metode som finner ut hvor mange unike tall det finnes i en sortert array
    public static int antallUlikeSortert(int[] a) throws IllegalStateException {
        if(a.length == 0)//dersom Array har 0 tall, er det 0 ulike verdier. Da returner vi 0
        {
            return 0;
        }

        for (int i = 0; i < a.length-1; i++) {//løkke som sjekker om det finnes et tall i arrayet som har lavere verdi til høyre for den. Dersom det finnes, vet vi at Arrayet ikke er sortert i stigende rekkefølge
            if (a[i] > a[i+1]){
                throw new IllegalStateException("Arrayet er ikke sortert i stigende rekkefølge!");
            }
        }
        int antall_ulike = 0; //teller som holder styr på antall ulike tall i arrayet

        for (int i=0;i<a.length;i++){//løkke som looper gjennom arrayet fra start til slutt
            boolean unik = true;//defineren en boolean som holder styr på om et element er unikt eller ikke. Denne endres til false dersom et tall ikke er unikt, og da plusses ikke antall_ulike videre i koden.
            for (int j=i+1;j<a.length;j++) {//loop som sjekker om alle elementene til høyre for et element a[i] har lik verdi som a[i] eller ikke.
                if (a[i] == a[j]) {//dersom det finnes et element til høyre for a[i] med lik verdi vil den ikke være unik og vi breaker ut.
                    unik = false;
                    break;
                }
            }
            if (unik){//dersom et tall holder seg unik (altså if-betingelsen over blir false), betyr det at antall_ulike skal økes med 1.
                antall_ulike++;
            }
        }
        return antall_ulike;//returnerer telleren for antall ulike tall i arrayet.
    }

    ///// Oppgave 3 //////////////////////////////////////
    //Metode som finner ut hvor mange ulike tall det er i en usortert array
    public static int antallUlikeUsortert(int[] a) {
        if(a.length == 0)//dersom Array har 0 tall, er det 0 ulike verdier. Da returner vi 0
        {
            return 0;
        }
        int antall_ulike = 0; //teller som holder styr på antall ulike tall i arrayet

        for (int i=0;i<a.length;i++){ //løkke som looper gjennom arrayet fra start til slutt
            boolean unik = true; //defineren en boolean som holder styr på om et element er unikt eller ikke. Denne endres til false dersom et tall ikke er unikt, og da plusses ikke antall_ulike.
            for (int j=i-1;j>=0;j--) {//loop som går gjennom alle elementene til venstre for a[i]
                if (a[i] == a[j]) {//dersom det finnes et element for venstre til a[i] med lik verdi vil den ikke være unik og vi breaker ut. Første gang vi får en verdi vil den være unik fordi samme verdi ikke finnes til venstre for den, men andre gang, tredje gang osv. vil den ikke være unik.
                    unik = false;
                    break;
                }
            }
            if (unik){//dersom et tall holder seg unik (altså if-betingelsen over blir false), betyr det at antall_ulike skal økes med 1.
                antall_ulike++;
            }
        }
        return antall_ulike;//returnerer telleren for antall ulike tall i arrayet.
    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {
        int oddetall = 0;
        for (int i = 0; i < a.length; i++) { //løkke som teller antall oddetall i array
            if (a[i] % 2 != 0) {
                int temp = a[i];
                a[i] = a[oddetall];
                a[oddetall] = temp;
                oddetall++;
            }
        }

        quicksort(a, 0, oddetall - 1);//sorterer oddetallene
        quicksort(a, oddetall, a.length-1);//sorterer partallene
    }

    public static void quicksort(int[] a, int left, int right) {
        if (left >= right) {//returnerer dersom a er tom eller har 1 element.
            return;
        }
        int midt = (left + right)/2; //definerer midtverdi

        bytt(a, midt, right); //bytter midterste verdi (skilleverdien) i arrayet til slutten.
        int pos = partisjon(a, left, right - 1, a[right]); //partisjonerer arrayet fra start til nest-sist siden siste verdi er skilleverdien. Tar vare på siste verdi som er mindre enn skilleverdien.
        bytt(a, pos, right); //flytter skilleverdien til riktig sortert plass
        quicksort(a, left, pos - 1);//gjentar for venstre sub-array
        quicksort(a, pos + 1, right);//og høyre sub-array
    }

    public static void bytt(int[] a, int i, int j) { //metode for å bytte plass på to verdier i et array.
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int partisjon(int[] a, int left, int right, int skilleverdi) {
        while (true) {
            while (left <= right && a[left] < skilleverdi) {
                left++;
            }
            while (left <= right && a[right] >= skilleverdi) {
                right--;
            }

            if (left < right) {
                bytt(a,left++,right--);
            } else return left;
        }
    }

    ///// Oppgave 5 //////////////////////////////////////
    //Metode som "roterer" alle karakterer i et array. Altså alle karakter flyttes et hakk til høyre og siste element blir første element.
    public static void rotasjon(char[] a) {
        if (a.length >= 2){//dersom array har lengde 0 eller 1, trenger den ingen rotasjon og vi går inn i if-betingelen bare hvis arrayet har lengde 2 eller større
            char siste_verdi = a[a.length-1]; //vi lagrer siste verdi med en hjelpevariabel slik at verdien til siste element ikke mistes i koden under
            for (int i=a.length-2;i>=0;i--){//vi kjører en loop som starter fra nest-siste verdi, og kjører helt til i=0
                a[i+1] = a[i];//flytter elementet til høyre
            }
            a[0] = siste_verdi;//"roterer" det siste elementet til starten av arrayet.
        }
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    //Metode som tar en og en bokstav fra to stringer, og skriver ut en ny string
    public static String flett(String s, String t) {
        StringBuilder ny_string = new StringBuilder();//definerer en ny StringBuilder som skal bygge en ny String som består av bokstavene fra de to andre Stringene
        if (s.length() < t.length()){//dersom String t er lengre enn String s går vi inn i denne if-setningen
            for (int i=0;i<s.length();i++){//kjører en loop som starter på 0, altså første bokstav i begge Stringene, og slutter når den har nådd lengden til den korteste stringen.
                ny_string.append(s.charAt(i));//legger til bokstavene fra Stringene. I første loop-runde vil det være første bokstav, andre loop-runde andre bokstav osv.
                ny_string.append(t.charAt(i));//
            }
            for (int i=s.length();i<t.length();i++){//siden ene Stringen er lengre enn den andre, kjører en ny loop som fullfører resten av den lengste Stringen
                ny_string.append(t.charAt(i));
            }
        }
        else if(s.length() > t.length()){//dersom String s er lengre enn String t går vi inn i denne if-setningen
            for (int i=0;i<t.length();i++){
                ny_string.append(s.charAt(i));
                ny_string.append(t.charAt(i));
            }
            for (int i=t.length();i<s.length();i++){
                ny_string.append(s.charAt(i));
            }
        }
        else{//dersom begge Stringene er like lange går vi inn i denne
            for (int i=0;i<t.length();i++){
                ny_string.append(s.charAt(i));
                ny_string.append(t.charAt(i));
            }
        }
        return String.valueOf(ny_string);//returnerer den nye stringen vi har bygget fra bokstavene
    }

    /// 7b)
    //Metode som tar inn flere Stringer, og bygger en ny String med en og en bokstav fra hver String som blir sendt inn
    public static String flett(String... s) {
        StringBuilder ny_string = new StringBuilder();//definerer en ny StringBuilder som skal bygge en ny String som består av bokstavene fra de andre Stringene

        int lengste_string = 0;//definerer en lengste string for å finne ut hvor mange bokstaver den lengste string har
        for (String item : s) {//looper gjennom alle Stringene og finner den lengste Stringen ved hjelp av hjelpevariabelen.
            if (lengste_string < item.length()) {
                lengste_string = item.length();
            }
        }
        for(int i = 0; i < lengste_string; i++){//loop som starter på 0 og kjører til lengden av den lengste Stringen som vi fant ut tidligere. Loopen holder Styr på hvilken index av bokstaver vi er på
            for (String value : s) {//looper gjennom Stringene som blir sendt inn.
                if (value.length() >= i + 1) {//dersom Stringen er lengre eller lik hvilken bokstav index vi er på, skal vi inn i if-betingelsen
                    ny_string.append(value.charAt(i));//Finner bokstav ved hjelp av bokstav index fra den ene loopen, og String index fra den andre loopen, og putter den bokstaven inn i den nye Stringer vi bygger
                }
            }
        }
        return String.valueOf(ny_string);//Returnerer den nye Stringen
    }

}  // Oblig1
