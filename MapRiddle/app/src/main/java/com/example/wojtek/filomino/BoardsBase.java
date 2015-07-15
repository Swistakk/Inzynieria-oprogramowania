package com.example.wojtek.filomino;

/**
 * Created by wojtek on 05.07.15.
 */
public class BoardsBase {

    public String[][] boards;
    public String getBoard(int n, int id) {
        return boards[n][id];
    }
    BoardsBase() {
        boards = new String[12][50];
        boards[6] = new String[] {
            "070010010163001000107101000000101202",
            "010021002000170000404501012000000103",
            "001010030080331010100100300602030104",
            "010030001010100002209000300210102300",
            "001000040210001400100001035200003501",
            "010000031310010020640007001000100004",
            "005800050500200002140401064005000010",
            "100007010022000100100070000020245010",
            "400010042300010410100107010000007040",
            "002025001000010400000201510000100702",
            "000410000004001001220060100000201019",
            "120001001000100212012001000100101040",
            "101000834020000010001050015020000101",
            "101000030001050300000912012000032010",
            "010001202000000314104050001201002000",
            "010000001000002062105000310017000021",
            "001000032024200001051000070060000701",
            "001000032024200001051000070060000701",
            "400100300010013000800000300001200060",
            "900000300202003000320002004107000001",
            "900000300202003000320002004107000001",
            "010000201010000080400003002121100080",
            "002001707000000102010000000008127006",
            "000100000010108000001047310020400400",
            "090102010010021000000100610010100102",
            "000240420000001030000012201000020061",
            "000000010401002100103201000062190100",
            "000006030100000300010123100000026101",
            "000003210004100003000005010200204200",
            "020002000001103100008005010020105100",
            "001021140100010020000010600052201010",
            "000020012350021000000610010004620002",
            "000010206042000000000030200025500004",
            "050043100000010100000230000000602106",
            "102000400000100121520002001010100030",
            "100000008760100000000006800600309001",
            "000090000916921000000101000000121071",
            "100400500120100210020030000130420000",
            "001002000001210010100000000820190060",
            "191080000000100510310100000000010010",
            "700031000005000017070000000170501040",
            "002100001001100108001000012010008020",
            "000600100400004140000010101360500010",
            "000010076100106001010810000000002103",
            "200000003030001000000190700240205000",
            "000002200941103000004000005000210010",
            "001003000010030100010030490923200000",
            "610000041021000000010010000103019000",
            "102210060007002090100040000200001000",
            "001000002030210810130000000010042031",
        };
        boards[7] = new String[] {
            "0001001100010240102401001200010030103551001201000",
            "4801000000013000100527000001006310001010500000030",
            "1000010041360008024040010010010000000600001002100",
            "0104102008000010200020010810150000001010010010300",
            "0001043012410010000000010210000100230001000151601",
            "0000400303030002200210000500010023000071006710210",
            "2000031003004003301000002001050120034070000021001",
            "1030020040001200000006120030103100201003010015000",
            "0200100000000313410066002100103004100100102001200",
            "0000101100020302107010100000000010510120310000040",
            "0002100000000080500106003200030200100010180000000",
            "0200700001000120300000028100010001020140200050100",
            "0000105021003001500100001000301000010030000009241",
            "0000102020000000316020400204010000004091444050000",
            "0021001000610610000100013170050000100000043012020",
            "0202003100000108100104000020100000030210160205001",
            "1000100002002022310100000003031000101316030000040",
            "0000001000009723120000100020000010000040175213002",
            "1000500202010201304010700010010010004700000010712",
            "0000090010008000500501021010004010115050000012010",
            "0000002042060000600600029010009006000001970080000",
            "0020050310010100000080031002100001005121002100008",
            "0000010010810007010030000400307010200000007909241",
            "0010610100002000010001000204310335000500202010040",
            "0000100001730022020000000014001060032000001004313",
            "0000000040001408230500010012000300303600330810002",
            "0100060000060010010200470310131080200000000201018",
            "0000219100000030010020100008000051010010020003140",
            "0003000031500001001000020301101002286680000000061",
            "1010100008000010410040060001000003000020122101071",
            "0140003030072005320001001002002200000101001090001",
            "0000100710000324015010030100030003004000400031020",
            "0070001000300000002100000500000014061020701602000",
            "0070000010034100030103200003010105102100040400200",
            "1001000010000207081300703010000100408000107140103",
            "0401200000000402003603420000000012001400000080310",
            "3018000010061000200030120130000001002010310100102",
            "0121000000043600100008003600400500001000032500330",
            "0003001000100310030100100072300004140201000010013",
            "1015000230000100010200008100021021000010021200501",
            "1801001002000310000510200300000000000034311001002",
            "0000200104101000100010085013310123010008200006001",
            "1000000002100846100200001024370000000060051000010",
            "0302001500100010000040002101103006000023040302010",
            "1010100200000000100000104070000503510050416000020",
            "0000100230103220100060000000200320110001027010401",
            "0001030000040324410020010010600810000202030210001",
            "2002010003009200001036030030500010001000003003124",
            "0000010005104217000000100000200002001010400385051",
            "0002103105100000103010000020310028000200300030000"
        };
        boards[8] = new String[] {
            "0000010060120010000020032100410100001400032102012010031000061040",
            "1000020600021001000000100600000000405151010910130400000090024041",
            "1000060000000041001030103204100001006103001000000100031410312102",
            "0100000200130001050601300000101252100001034001221020006000003201",
            "0001010060302001021000030100400010010210000200605150001000041680",
            "0014000131008400070000000120010005003012100010204023001010230800",
            "1000200230010000007000002410210020033030001040153000140012100082",
            "0000380005010020310001001670400100001302200002010000030000001500",
            "0250000004014010000052301300000001201301203000000013021041020501",
            "0000020006207002000000006310004001003100001019000042000254024300",
            "0500700000020210010230000300010700125022200010600018000000081201",
            "0201007002002000000001031000032003196010020000003201310100100070",
            "0701002510000000050001032000000003120050025000020000200402103501",
            "0701002310000000821040010002200720000400000024010230005000000210",
            "0000001005000100005300411070000000181021300101000000041521321000",
            "0014053010000001000030602230002041203000000010002005003210010060",
            "0030020000004320000000002030510500000021021210040000001035120003",
            "0001000220100000002101000002082003000106000010000210000131023207",
            "0000000401000450002420001000121301520000052101000005000010030016",
            "0002302621020000700000000001005610000012030472002000000050125000",
            "0400100000100010402100411400087700010007101010100130002000000313",
            "0001010110100000402010013406000007000100000400100231020000000064",
            "0130080000010200010020003000010010002006032104021000060000100063",
            "0030100240030100040100052000041000204302001021010200500407010101",
            "0100000000003192010007000000000041017215200001000000003231305031",
            "1000000001520412040400401000300004010064000070010027300020014002",
            "1310000100001000003025134000010020001000100100240220230100103004",
            "1001000001001000000000025800170400010021021000103100020202105100",
            "0003000000004106453000001300220400000602040100000007001040312100",
            "0100030003001502001301001000000006020000102106000030304003301212",
            "1000310102002323040100100800145002000001001001003021200000030101",
            "0002200105100000100000640020304141000200100005100100100003204210",
            "0001000103408000100000000200010001020673008100010000000519101302",
            "0020020000100530135130010700130001000010000030060321000000200109",
            "0000020104100100010002510001010001630603700000002000021010010320",
            "0450021700010000100002100001003201000100300100001250010060000212",
            "0000101000000030400303000781412001004900008900017040106014900912",
            "0021000101001002200200171010400000001012010002002302300102012000",
            "6000070081000140000006200100200000100200010121000000000203105100",
            "0000018010000001402061001010000200543101001200003001002013240100",
            "0000000002012020030200091004191060100033000421416341244461404122",
            "3100000100012560310005000001090101903900002042012010000014000100",
            "0005020010000181001000022095510019910001302021040010050010000310",
            "0000010001720400000301262100006010017000002001000604031021020020",
            "0000000010124071000001300503000122010100019000010001003041001212",
            "1000001050000000025100100000179040100002000043010101000019001503",
            "0101900200000000601012100100010100080503108102100000084005810201",
            "0000150000120300000410010900300201060000022100101000602000141025",
            "0031000802000000420510030002002400006030101000200022302001000007",
            "0100100330010004100001000105001400018002301031036040000110100000"
        };
        boards[9] = new String[] {
            "290000200065102103000000310100021001000000810010100020030201700010000104030310071",
            "100100035020700130310520620044001000040019000001220000230071010131000003000012133",
            "001004031010012100003000000200100020102010017001032100032001200017500000000500562",
            "000200020014440010030101000101020053004093101210010000022020530310030014300301220",
            "010134001032000800005000001301248000000100100010601000000107010010210105400320030",
            "305303002040100000000000000010012340080000030038400018101072004000005130070001000",
            "100105150820010000000200002101002101303000000000500302000000009201745002100071009",
            "001000800100050280010300910004100100003000000105400121201001303100013000301021000",
            "000030200210330500000000010020600602000794010060012000000000014016220002000130004",
            "000710010000280002010310020100100100020003004030000101002008503203010100101230001",
            "000102001004000000200000616100230000403180904131001001300016012200081600200312000",
            "005002000100009019500100005000322001020103005420041000000030000170036070021666031",
            "000102000241060000010074003100300000830000030000020304101000000803002013024001041",
            "000200201001001030130310012004006103021320210000030001142120080302000103010010080",
            "000101030200350210201000200050210101127000302000100006017510010800000120000100041",
            "000060004181000000000020202100010603021831613007732020100132510322551555031005122",
            "002004003000004151009210000102120012000010100004000000020460143016016200500020003",
            "200100001000710000000000090213000104031043020002018430000020060030010400021000100",
            "010000000001513030010004212050000101610001000200120003001000300312030100020000842",
            "000057008050001000010000002504005000103010802201501401200800100108006031000122302",
            "000210100101500210200300004062010500020004410000101401010000000000200152124130330",
            "000010046030100000200000000101012102240200000000010018001002100022740002310304551",
            "022140004040410200300400140007020000108000198801079010140010200002020101204104404",
            "000100000206071301000100100010300002100026501032003000013021000003109201005200000",
            "000000090203031092101000014000231000410000100020016000001302023003007000004200417",
            "300004100020001000010010021052030010015006100000102001043502100201200801100000102",
            "000200410031004002010020020003040100100440002309402001200005040013102033033002009",
            "000122010010000007200010000401001010102003050000500013230210601000630203101000101",
            "010013000007000000010012102000000200623600000030060210830050053000344040103300006",
            "050000210002100000100000601001250070002120060001001070010010010401600445103200004",
            "500101000000000001000013123071070400000100410023010105510202400022002012600031000",
            "000020020030030313003200000412000340000310000700150100010201900221200202000001201",
            "000000402071000003023030001100400100010000270202000140000300031060140000210200314",
            "001001021010000000030108180065000020000200030000161004210010021101360010000101032",
            "501000100029050001001010000100050141000101010310000002100007101024707250050020212",
            "000001000100006010027615240010000100000045200320180007000231001001000000230210008",
            "000060100000101000014009212001002101300010320030002000213001600020010831038000800",
            "002038010000000000100000130530202023000103600018400013004443001312020000002003220",
            "031700040000006002001260101100000605500010709010200701000100200161000000024010002",
            "000002000020100010005030490201040000034210121100100000000301010010013030051060019",
            "042100100000000205010103100000001500000500204262100106001012001000230100420040020",
            "001000100010001000047100212301000100000900020001030130010100021300320010141004550",
            "000001000000120020205001410301204044107000051001010100022001003041000104450504021",
            "002042700007000100000014003203800041007000000010300101009380810101010044004041010",
            "003008101002010430050020040010050000020027201520027502100610050210000210000217040",
            "000801003001005060005001003100400100009300030902140015002010000010007230001010040",
            "000200000001910000100094002008000300080010084500008001300161010000020100210800063",
            "000010000401200010012801000000300003360010012101209040000441001010212004240001003",
            "000001000010000051002505310200023000000314001300100002102031001300700203100133002",
            "008040030000001040002360066402330500100190215210000090003102030001221930900000091"
        };
        boards[10] = new String[] {
            "1020020000001003037000000000020001260610016010120080210302011010100154800230300010010120000010041032",
            "1000040102001001300110040200000210210001100010210302600103411300140300221020100110331201002010213000",
            "0700000007102100007000402006102100102500120102500800023125100152332500900099171800190700401331007044",
            "0030002002010051005000100301020100400006501002002126020120800000300010000201004113010000005003012004",
            "0000001031703032600010216000120720000881121012270030002000100050210073010103210103120310221031006001",
            "0000200050001001934021008009000041010000010203004080100020100000212006210000510612000602000306100141",
            "0000006020000000093000006100100700100102001202000301000003010213051002100141030200040201011010150002",
            "0021000024081000004020020031041002001000000900410001010100210304032010300600003310101230606000430012",
            "0101002020000803101030100002001002016101001032000000010131011004000009001200100141500120101001007000",
            "0000040000300007008101500400004201021000000040017100004100003003001612053007030020001000001420001034",
            "0000400001000950040000010012000102000100260403460060000200107001012030077040001001004610021600400104",
            "0300003300800301000100000207200001020000231010053000024010132100100000001082102010030000070060013104",
            "1001201020010001010800100900000200001530002010005067000021000160014300031040001241000103040001500100",
            "0000100010103202000230010151010210430300010000009040102612201001210103020500300302004201000000100410",
            "1000309500000021010110100000002000100000131020109300001502121000510100000120340417200001000021512001",
            "0020310200019010103000010020300102012031008000000010310000000180010930000810502021004000032000100180",
            "0000000102141007000301007505000000000003010903000010041602800000001001000102010600400040203416102100",
            "2001049000001400010100200000001014001001000071900610109300012000012024101040001000004306004101400100",
            "0000000001001012001051000003021023272100000100025031007102591314122190533701400005107040010070014102",
            "0002002020410007400000014040002100100830040121831041000000344000004550043040000022100232231008144403",
            "0070000002001010008800050230000200109000030100600001294160880000100100010400100000400005209219105054",
            "0002000001000001013010700302020223010201040905014019000080422102010081233709912001307072300005001010",
            "0020020001003000840010000003000012230103440010402020122106138080606350022624000401062445548816604010",
            "3000610300130000000800447201072030018000807000021000014101001000072303001000010020804107301012400013",
            "0010002100045006100100315030520046000600020030100501000625553245100330101020130100002000800210010012",
            "0001000003000050003304007603100710600000000510010010100010003002500029001800100102000022300313250001",
            "0000024000010000010010000040104018250000100000062000321631090500010321102105040002152000100000200540",
            "0031000000001210801410000002000107108132000004101003121200000002000004010510001020130010031040308602",
            "0010020001108000090000101000160000000004000000200201983000010010121402900001020110740431003322143350",
            "0000000000002001300062101002130000020051130410031302120010000000100001101024010501231000133023315130",
            "1001001001031000010010000000512081021402080000033141015443100405201200010516020105660001025561208000",
            "0001001001303004041012121600400006004041808162010200100010048071012201010013101223270300023327131221",
            "0020010016000590100010100000400204000631000300000000010106011820260020000000010320101410061450020001",
            "0100200401060001001026910200052003040100410001001000200010201002106020310000101600000220000035004032",
            "0203000010000083000000000413040121010002100710000100240321000001001201100500030000100003322160010001",
            "1040102000001230002010001060108100340243000100010001001002010310200000160010010700100613010100003102",
            "0105000102060012001060000503000555031200021001300005000210212000100010001223150231000002101026000130",
            "1000001000024001220000305001911704000002040020000020003210011000720640000000000100109001021032403001",
            "0100021003000300023310100100600200001000008430001500102020021000101041020323944313012004133309091223",
            "0002000060041000010210206060010443100303214001214210600052000630510004201310000010000220900001081021",
            "0100002120002001060022402010015001000000800021230306600061200008100010101029103220000002170001000000",
            "0060000740801012100100240020000660680020004000002010040000000020008109107221081000010001030000420041",
            "0001000001010004440204412500300080151213918500444499610016019060780860019070806000061000632012272803",
            "0100120101001001060022005000221000100001021640310000010209080030010181100203900204000100000351400401",
            "0001010020100000810030100000410101026200062003010000100002511000030102200016400010020000353086000001",
            "0001010000121030012200512450090550000320101000300032441030103101005000000026050110251610000025030122",
            "0021000021000821703010012000030010000260104015410000100010020000000301002050012020122020101021057000",
            "0020100020041002000401051510101021000202071041010300070000140000041000715712602000100216011500006022",
            "0001000003004005210200310200102606101090016000000000100100100202080103410001020000412003051600002102",
            "0220000000000100212010004005300000020100232042000310090040210105100030205500021003300013040402305100"
        };
        boards[11] = new String[] {
            "0001084020001000000000087001000000120022900080100000001300010001002120071200000010001310010419200020007013000010012001019",
            "1710000010300400000000000615200000140010500000150500203100031231070101000034005002020000401020161462000104000010060020200",
            "0001001000001003031003030200107000010700010002000010032000020414016021001020013600000100010100030400213024000202040015021",
            "0840001000320020000101201010200000000301021010030702120010921510012010000000330900500102122010720125010200200010002401021",
            "0600005000810101001000010000101000341220532130000000000012400510504002140212250100300400000103010051220030040100010210007",
            "0010002100333040400104004000010010002010000231010301910002010041000104003002450010910005010000003102025001015210500020005",
            "0004000120570012240100200000303003001010120000001000003021000101010102200000030305012101200212210002000061510055100505021",
            "0000002100010010000450036000200041040000008000101030000100902124002001010012013300084010244401242402142232413312210012213",
            "1001001001209202001000000012001000271001000801000000101000000010500210020001210030070020020200103000201000140001000980212",
            "0002000002535020002000020037000001810400000100000812100006100210100100034000724000610001404000015100020031314233132133000",
            "0020024000000050900071051001000403201000003131000105102030220502000000000140208001850040022034401010504101025010010001001",
            "1000010028130001000000201070001000400810240102401021000034004140120001300120321001000001101030406020080800000141004041000",
            "0009100103010030040100000214100000010200024215308002121000000100003000126001010100081000400002100811010106101200000010302",
            "0003102101000000710000008002030008101030427080071000312080070001010150121000500100200101020301320300000000201264131014041",
            "0201041000110000200003000103001010010001500008024400001045000137100022102160050000102610140013006000700310302000041040310",
            "0003020000400721023002401044000410020001000200002200001100014031000210020000115001001000013000203700010002001332401014021",
            "0000001061010064201002010100000002500140220031060007101032012320090010020910000700100201000103000100500020100004150122013",
            "0800020000020010032010100303140300000502050120001060102201000260000000060101014003030000020083004000120332003430200130120",
            "0000020000013002000020070030100430600006200010501010020041002041341022001830031010060822000013600013220632101010301320033",
            "1001023100001000100000800200320610021020100040400002420100010021200216304141540040522400012100005002320501210103102442044",
            "1006101010000100200010200020201014001204100500000100010249090001001420032120254006000201000500020400000201403031041030331",
            "0001301021000001020042216100000000020050833100100081010305130100020012030701050020000103522001062215900102061210013000402",
            "0102002000190000131010201003001041000250050140213301200010001020040220000120000002100041622100064100400130260304103000010",
            "0000020001030301300005100000100010021400103041001000012100102610000610001502003004500200010102010511000013201000520313000",
            "0003002100000210060100503001000310140090100500021030000013000121201209000101301010120300000002103510166020040008100103021",
            "0010000700130010200006200401013010621020331000200320103010031200221020031000000212000100040334100500010000026037000402101",
            "0020000000100090100100016009000031012150260100000210300234000020021001000302001030120130000000006139403002000010041040203",
            "0000000020040010021000001000054102000120120040206000230016013500703000000071030192301404000073001041440000600002141710012",
            "0100000003040191020010060020010016020100000000003100003026102121091000002000181836004310000004410301343000001000044104130",
            "3201000000601050200100001000026016000419913000040210031100410092200030702100045550000132141303012022000213403120012004102",
            "0000010001000020004100210080000101010745520200600100101006600516000100710010092070200012020081010000100004314318021233223",
            "3020000300100100030103200500402310200310030001302012002051001301000001000100442031010131012201044100431501020200322001001",
            "0000000200014100010061007080270000100000410060102030320000000130162031012000100240040000006001051200130030020006130021034",
            "0000000000002270201210501004001201401040231061000107000000000010010603201010201201502010031001233000290202002412090010414",
            "0021010030050000032140200140103031002122010002120600040010310100050001000125002122100030000000221255131530402320150310133",
            "0070004023010010000100501010010011020015020000121010170005000000102053010203712000302153313060000000032100221030012030002",
            "0208020000010001002501000106051060010021050110300000003018000000200050010100000001020010002030140000341000051070001063100",
            "0039000000000000001019010000200090040431091000191000000200200218100010015010200040750001500100041300130015101200420302031",
            "0000010010210612000005000749010200000020000010003140105001000060051321000100721000004101008018010200010200000020002512007",
            "1041000100000020314160000001443067101030413623150101002205544001020310100070101032400012423121510600210303261333040412030",
            "0012001003002000021010012170200233000401012000501002210001300000300100020600937001001002101020629130100301200330122051022",
            "0200000000000104000000200600154301004002025003500010000241491000300001000330010120000103010109300010300010516010510005201",
            "0000100100000003200010020301053500501003102042000009001071070010000100108851300210008008007000280231010212162380082000600",
            "0080000202010002000200003061500010010021031000001000900028100120001000215001008031053106013300000000050013410115014033040",
            "6001001030309002000010101200000000000100880901006001001121001001230001000520001301320001902902100070000500100410100102100",
            "0220300000000033200001000900510001001210034001200010410006020031523030013000040330007102100052100000002000005131052213102",
            "0000000090000352043109040005042000003100009106102120000130000207033021021063310500100310212200003002340100021000300000460",
            "0000007001030000301000203007001091002001031033000100100034700100600000106131000210312006070710710400001010000421730201002",
            "0702003100000000004210001000091021050010204105001001000301500100141020010170056100500010000001381020014058000351000100021",
            "0020000000001003000310001400210020031010000101000000150501082403120040710210000170071020210010060001306002100102120614002"
        };
    }
}