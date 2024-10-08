package org.modernbank.backend.party.utilities.model.type;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@Schema(
    name = "Country code",
    description = "Country codes available")
@JsonInclude(Include.NON_NULL)
public enum TypeCountry {
    AF ("Afghanistan", "AFG", "4"),
    AX ("Åland Islands", "ALA", "248"),
    AL ("Albania", "ALB", "8"),
    DZ ("Algeria", "DZA", "12"),
    AS ("American Samoa", "ASM", "16"),
    AD ("Andorra", "AND", "20"),
    AO ("Angola", "AGO", "24"),
    AI ("Anguilla", "AIA", "660"),
    AQ ("Antarctica", "ATA", "10"),
    AG ("Antigua And Barbuda", "ATG", "28"),
    AR ("Argentina", "ARG", "32"),
    AM ("Armenia", "ARM", "51"),
    AW ("Aruba", "ABW", "533"),
    AU ("Australia", "AUS", "36"),
    AT ("Austria", "AUT", "40"),
    AZ ("Azerbaijan", "AZE", "31"),
    BS ("Bahamas", "BHS", "44"),
    BH ("Bahrain", "BHR", "48"),
    BD ("Bangladesh", "BGD", "50"),
    BB ("Barbados", "BRB", "52"),
    BY ("Belarus", "BLR", "112"),
    BE ("Belgium", "BEL", "56"),
    BZ ("Belize", "BLZ", "84"),
    BJ ("Benin", "BEN", "204"),
    BM ("Bermuda", "BMU", "60"),
    BT ("Bhutan", "BTN", "64"),
    BO ("Bolivia (Plurinational State Of)", "BOL", "68"),
    BQ ("Bonaire, Sint Eustatius And Saba", "BES", "535"),
    BA ("Bosnia And Herzegovina", "BIH", "70"),
    BW ("Botswana", "BWA", "72"),
    BV ("Bouvet Island", "BVT", "74"),
    BR ("Brazil", "BRA", "76"),
    IO ("British Indian Ocean Territory", "IOT", "86"),
    BN ("Brunei Darussalam", "BRN", "96"),
    BG ("Bulgaria", "BGR", "100"),
    BF ("Burkina Faso", "BFA", "854"),
    BU ("Burma", "BUR", "104"),
    BI ("Burundi", "BDI", "108"),
    CV ("Cabo Verde", "CPV", "132"),
    KH ("Cambodia", "KHM", "116"),
    CM ("Cameroon", "CMR", "120"),
    CA ("Canada", "CAN", "124"),
    IC ("Canary Islands", "-", ""),
    CT ("Canton And Enderbury Islands", "CTE", "128"),
    KY ("Cayman Islands", "CYM", "136"),
    CF ("Central African Republic", "CAF", "140"),
    EA ("Ceuta, Melilla", "-", "-"),
    TD ("Chad", "TCD", "148"),
    CL ("Chile", "CHL", "152"),
    CN ("China", "CHN", "156"),
    CX ("Christmas Island", "CXR", "162"),
    CP ("Clipperton Island", "-", "-"),
    CC ("Cocos (Keeling) Islands", "CCK", "166"),
    CO ("Colombia", "COL", "170"),
    KM ("Comoros", "COM", "174"),
    CG ("Congo", "COG", "178"),
    CD ("Congo, Democratic Republic Of The", "COD", "180"),
    CK ("Cook Islands", "COK", "184"),
    CR ("Costa Rica", "CRI", "188"),
    CI ("Côte D'Ivoire", "CIV", "384"),
    HR ("Croatia", "HRV", "191"),
    CU ("Cuba", "CUB", "192"),
    CW ("Curaçao", "CUW", "531"),
    CY ("Cyprus", "CYP", "196"),
    CZ ("Czechia", "CZE", "203"),
    DY ("Dahomey", "DHY", "-"),
    DK ("Denmark", "DNK", "208"),
    DG ("Diego Garcia", "-", "-"),
    DJ ("Djibouti", "DJI", "262"),
    DM ("Dominica", "DMA", "212"),
    DO ("Dominican Republic", "DOM", "214"),
    NQ ("Dronning Maud Land", "ATN", "216"),
    TP ("East Timor", "TMP", "626"),
    EC ("Ecuador", "ECU", "218"),
    EG ("Egypt", "EGY", "818"),
    SV ("El Salvador", "SLV", "222"),
    GQ ("Equatorial Guinea", "GNQ", "226"),
    ER ("Eritrea", "ERI", "232"),
    EW ("Estonia", "EST", "233"),
    EE ("Estonia", "EST", "233"),
    SZ ("Eswatini", "SWZ", "748"),
    ET ("Ethiopia", "ETH", "231"),
    EU ("European Union", "-", "-"),
    FK ("Falkland Islands (Malvinas)", "FLK", "238"),
    FO ("Faroe Islands", "FRO", "234"),
    FJ ("Fiji", "FJI", "242"),
    SF ("Finland", "-", "-"),
    FI ("Finland", "FIN", "246"),
    FR ("France", "FRA", "250"),
    FX ("France, Metropolitan", "FXX", "249"),
    GF ("French Guiana", "GUF", "254"),
    PF ("French Polynesia", "PYF", "258"),
    FQ ("French Southern And Antarctic Territories", "ATF", "-"),
    TF ("French Southern Territories", "ATF", "260"),
    GA ("Gabon", "GAB", "266"),
    GM ("Gambia", "GMB", "270"),
    GE ("Georgia", "GEO", "268"),
    DD ("German Democratic Republic", "DDR", "278"),
    DE ("Germany", "DEU", "276"),
    GH ("Ghana", "GHA", "288"),
    GI ("Gibraltar", "GIB", "292"),
    GR ("Greece", "GRC", "300"),
    GL ("Greenland", "GRL", "304"),
    WG ("Grenada", "GRD", "308"),
    GP ("Guadeloupe", "GLP", "312"),
    GU ("Guam", "GUM", "316"),
    GT ("Guatemala", "GTM", "320"),
    GG ("Guernsey", "GGY", "831"),
    GN ("Guinea", "GIN", "324"),
    GW ("Guinea-Bissau", "GNB", "624"),
    GY ("Guyana", "GUY", "328"),
    HT ("Haiti", "HTI", "332"),
    HM ("Heard Island And Mcdonald Islands", "HMD", "334"),
    VA ("Holy See", "VAT", "336"),
    HN ("Honduras", "HND", "340"),
    HK ("Hong Kong", "HKG", "344"),
    HU ("Hungary", "HUN", "348"),
    IS ("Iceland", "ISL", "352"),
    IN ("India", "IND", "356"),
    ID ("Indonesia", "IDN", "360"),
    RI ("Indonesia", "IDN", "360"),
    IB ("International Bureau Of Wipo", "-", "-"),
    IR ("Iran (Islamic Republic Of)", "IRN", "364"),
    IQ ("Iraq", "IRQ", "368"),
    IE ("Ireland", "IRL", "372"),
    IM ("Isle Of Man", "IMN", "833"),
    IL ("Israel", "ISR", "376"),
    IT ("Italy", "ITA", "380"),
    JM ("Jamaica", "JAM", "388"),
    JA ("Jamaica", "JAM", "388"),
    JP ("Japan", "JPN", "392"),
    JE ("Jersey", "JEY", "832"),
    JT ("Johnston Island", "JTN", "396"),
    JO ("Jordan", "JOR", "400"),
    KZ ("Kazakhstan", "KAZ", "398"),
    KE ("Kenya", "KEN", "404"),
    KI ("Kiribati", "KIR", "296"),
    KP ("Korea (Democratic People'S Republic Of)", "PRK", "408"),
    KR ("Korea, Republic Of", "KOR", "410"),
    KW ("Kuwait", "KWT", "414"),
    KG ("Kyrgyzstan", "KGZ", "417"),
    LA ("Lao People'S Democratic Republic", "LAO", "418"),
    LV ("Latvia", "LVA", "428"),
    LB ("Lebanon", "LBN", "422"),
    RL ("Lebanon", "LBN", "422"),
    LS ("Lesotho", "LSO", "426"),
    LR ("Liberia", "LBR", "430"),
    LY ("Libya", "LBY", "434"),
    LF ("Libya Fezzan", "-", "-"),
    LI ("Liechtenstein", "LIE", "438"),
    FL ("Liechtenstein", "LIE", "438"),
    LT ("Lithuania", "LTU", "440"),
    LU ("Luxembourg", "LUX", "442"),
    MO ("Macao", "MAC", "446"),
    MK ("Macedonia, The Former Yugoslav Republic Of", "MKD", "807"),
    MG ("Madagascar", "MDG", "450"),
    RM ("Madagascar", "MDG", "450"),
    MW ("Malawi", "MWI", "454"),
    MY ("Malaysia", "MYS", "458"),
    MV ("Maldives", "MDV", "462"),
    ML ("Mali", "MLI", "466"),
    MT ("Malta", "MLT", "470"),
    MH ("Marshall Islands", "MHL", "584"),
    MQ ("Martinique", "MTQ", "474"),
    MR ("Mauritania", "MRT", "478"),
    MU ("Mauritius", "MUS", "480"),
    YT ("Mayotte", "MYT", "175"),
    MX ("Mexico", "MEX", "484"),
    FM ("Micronesia (Federated States Of)", "FSM", "583"),
    MI ("Midway Islands", "MID", "488"),
    MD ("Moldova, Republic Of", "MDA", "498"),
    MC ("Monaco", "MCO", "492"),
    MN ("Mongolia", "MNG", "496"),
    ME ("Montenegro", "MNE", "499"),
    MS ("Montserrat", "MSR", "500"),
    MA ("Morocco", "MAR", "504"),
    MZ ("Mozambique", "MOZ", "508"),
    MM ("Myanmar", "MMR", "104"),
    NA ("Namibia", "NAM", "516"),
    NR ("Nauru", "NRU", "520"),
    NP ("Nepal", "NPL", "524"),
    NL ("Netherlands", "NLD", "528"),
    AN ("Netherlands Antilles", "ANT", "530"),
    NT ("Neutral Zone", "NTZ", "536"),
    NC ("New Caledonia", "NCL", "540"),
    NH ("New Hebrides", "NHB", "-"),
    NZ ("New Zealand", "NZL", "554"),
    NI ("Nicaragua", "NIC", "558"),
    NE ("Niger", "NER", "562"),
    RN ("Niger", "NER", "562"),
    NG ("Nigeria", "NGA", "566"),
    NU ("Niue", "NIU", "570"),
    NF ("Norfolk Island", "NFK", "574"),
    MP ("Northern Mariana Islands", "MNP", "580"),
    NO ("Norway", "NOR", "578"),
    OM ("Oman", "OMN", "512"),
    PC ("Pacific Islands (Trust Territory)", "PCI", "582"),
    PK ("Pakistan", "PAK", "586"),
    PW ("Palau", "PLW", "585"),
    PS ("Palestine, State Of", "PSE", "275"),
    PA ("Panama", "PAN", "591"),
    PZ ("Panama Canal Zone", "PCZ", "-"),
    PG ("Papua New Guinea", "PNG", "598"),
    PY ("Paraguay", "PRY", "600"),
    GC ("Patent Office Of The Cooperation Council For The Arab States Of The Gulf (Gcc)", "-", "-"),
    GD ("Grenada", "-", "-"),
    PE ("Peru", "PER", "604"),
    PH ("Philippines", "PHL", "608"),
    RP ("Philippines", "PHL", "608"),
    PI ("Philippines", "PHL", "608"),
    PN ("Pitcairn", "PCN", "612"),
    PL ("Poland", "POL", "616"),
    PT ("Portugal", "PRT", "620"),
    PR ("Puerto Rico", "PRI", "630"),
    QA ("Qatar", "QAT", "634"),
    RE ("Réunion", "REU", "638"),
    RO ("Romania", "ROU", "642"),
    RU ("Russian Federation", "RUS", "643"),
    RW ("Rwanda", "RWA", "646"),
    BL ("Saint Barthélemy", "BLM", "652"),
    SH ("Saint Helena, Ascension And Tristan Da Cunha", "SHN", "654"),
    KN ("Saint Kitts And Nevis", "KNA", "659"),
    LC ("Saint Lucia", "LCA", "662"),
    MF ("Saint Martin (French Part)", "MAF", "663"),
    PM ("Saint Pierre And Miquelon", "SPM", "666"),
    VC ("Saint Vincent And The Grenadines", "VCT", "670"),
    WV ("Saint Vincent And The Grenadines", "VCT", "670"),
    WS ("Samoa", "WSM", "882"),
    SM ("San Marino", "SMR", "674"),
    ST ("Sao Tome And Principe", "STP", "678"),
    SA ("Saudi Arabia", "SAU", "682"),
    SN ("Senegal", "SEN", "686"),
    RS ("Serbia", "SRB", "688"),
    CS ("Serbia And Montenegro", "SCG", "891"),
    SC ("Seychelles", "SYC", "690"),
    SL ("Sierra Leone", "SLE", "694"),
    SG ("Singapore", "SGP", "702"),
    SX ("Sint Maarten (Dutch Part)", "SXM", "534"),
    SK ("Slovakia", "SVK", "703"),
    SI ("Slovenia", "SVN", "705"),
    SB ("Solomon Islands", "SLB", "90"),
    SO ("Somalia", "SOM", "706"),
    ZA ("South Africa", "ZAF", "710"),
    GS ("South Georgia And The South Sandwich Islands", "SGS", "239"),
    SS ("South Sudan", "SSD", "728"),
    RH ("Southern Rhodesia", "RHO", "-"),
    ES ("Spain", "ESP", "724"),
    LK ("Sri Lanka", "LKA", "144"),
    SD ("Sudan", "SDN", "729"),
    SR ("Suriname", "SUR", "740"),
    SJ ("Svalbard And Jan Mayen", "SJM", "744"),
    SE ("Sweden", "SWE", "752"),
    CH ("Switzerland", "CHE", "756"),
    SY ("Syrian Arab Republic", "SYR", "760"),
    TW ("Taiwan, Province Of China", "TWN", "158"),
    TJ ("Tajikistan", "TJK", "762"),
    TZ ("Tanzania, United Republic Of", "TZA", "834"),
    TH ("Thailand", "THA", "764"),
    TL ("Timor-Leste", "TLS", "626"),
    TG ("Togo", "TGO", "768"),
    TK ("Tokelau", "TKL", "772"),
    TO ("Tonga", "TON", "776"),
    TT ("Trinidad And Tobago", "TTO", "780"),
    TA ("Tristan Da Cunha", "-", "-"),
    TN ("Tunisia", "TUN", "788"),
    TR ("Turkey", "TUR", "792"),
    TM ("Turkmenistan", "TKM", "795"),
    TC ("Turks And Caicos Islands", "TCA", "796"),
    TV ("Tuvalu", "TUV", "798"),
    UG ("Uganda", "UGA", "800"),
    UA ("Ukraine", "UKR", "804"),
    EF ("Union Of Countries Under The European Community Patent Convention", "-", "-"),
    AE ("United Arab Emirates", "ARE", "784"),
    GB ("United Kingdom Of Great Britain And Northern Ireland", "GBR", "826"),
    UK ("United Kingdom Of Great Britain And Northern Ireland", "GBR", "826"),
    UM ("United States Minor Outlying Islands", "UMI", "581"),
    PU ("United States Miscellaneous Pacific Islands", "PUS", "849"),
    US ("United States Of America", "USA", "840"),
    HV ("Upper Volta", "HVO", "854"),
    UY ("Uruguay", "URY", "858"),
    SU ("Ussr", "SUN", "810"),
    UZ ("Uzbekistan", "UZB", "860"),
    VU ("Vanuatu", "VUT", "548"),
    VE ("Venezuela (Bolivarian Republic Of)", "VEN", "862"),
    YV ("Venezuela (Bolivarian Republic Of)", "VEN", "862"),
    VN ("Viet Nam", "VNM", "704"),
    VD ("Viet-Nam, Democratic Republic Of", "VDR", "-"),
    VG ("Virgin Islands (British)", "VGB", "92"),
    VI ("Virgin Islands (U.S.)", "VIR", "850"),
    WK ("Wake Island", "WAK", "872"),
    WF ("Wallis And Futuna", "WLF", "876"),
    EH ("Western Sahara *", "ESH", "732"),
    WO ("World Intellectual Property Organization", "-", "-"),
    YE ("Yemen", "YEM", "887"),
    YD ("Yemen, Democratic", "YMD", "720"),
    YU ("Yugoslavia", "YUG", "891"),
    ZR ("Zaire", "ZAR", "180"),
    ZM ("Zambia", "ZMB", "894"),
    ZW ("Zimbabwe", "ZWE", "716");

    private String countryName;
    private String threeDigitCode;
    private String numericCode;

    TypeCountry(String countryName, String threeDigitCode, String numericCode) {
        this.countryName = countryName;
        this.threeDigitCode = threeDigitCode;
        this.numericCode = numericCode;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getThreeDigitCode() {
        return this.threeDigitCode;
    }

    public String getNumericCode() {
        return this.numericCode;
    }
}
