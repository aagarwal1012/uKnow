package com.stackoverflow.uknow.Classes.Questions;

import java.util.ArrayList;
import java.util.List;

public class GetQuestions {
    public List<Questions> english_single_correct = new ArrayList<>();
    public List<Questions> electronics_single_correct = new ArrayList<>();
    public List<Questions> electrical_single_correct = new ArrayList<>();
    public List<Questions> logic_single_correct = new ArrayList<>();
    public List<Questions> Basic_C_single_correct = new ArrayList<>();
    public List<Questions> civil_single_correct = new ArrayList<>();
    public List<Questions> CSE_single_correct = new ArrayList<>();
    public List<Questions> mechanical_single_correct = new ArrayList<>();
    public List<Questions> personality_single_correct = new ArrayList<>();


    public GetQuestions() {

        Questions questions30 = new Questions(1,
                "Direction: Select the option that is most nearly opposite in meaning to the given word.\n" +
                "VANITY (OPPOSITE)", "B", "Pride", "Humility",
                "Conceit", "Indifference");
        english_single_correct.add(questions30);
        Questions questions31 = new Questions(2,"Find the correct spelt word.","A","Adventitious","Adventitous","Adventitus","Adventituous");
        english_single_correct.add(questions31);
        Questions questions32 = new Questions(3,"Direction: Select the correct option that fills the blank(s) to make the sentence\n" +
               "grammatically correct.\n" +
               "The success that he has gained, though striking enough, does not, however, commensurate\n" +
               "the efforts made by him.","C","about","from","with","beside");
        english_single_correct.add(questions32);
        Questions questions33 = new Questions(4,"The small child does whatever his father was done.\n","C","has done","did","does", "had done");
        english_single_correct.add(questions33);
        Questions questions34 = new Questions(5, "The man to who I sold my house was a cheat.","D","to whom I sell","to who I sell","who was sold to", "to whom I sold");
        english_single_correct.add(questions34);
        Questions questions35 = new Questions(6, "To keeps one's temper.","B","To become hungry","To be in good mood","To preserve ones energy", "To be aloof from");
        english_single_correct.add(questions35);
        Questions questions36 = new Questions(7, "Meaning of FOSTERING:","D", "Safeguarding","Neglecting","Ignoring", "Nurturing");
        english_single_correct.add(questions36);
        Questions questions37 = new Questions(8, "Meaning of FOSTERING:","A", "RQPS", "QSPR","SQPR", "PQRS");
        english_single_correct.add(questions37);
        Questions questions38 = new Questions(1,"Which one is most appropriate dynamic system?","A","y(n) + y(n - 1) + y(n + 1)","y(n) + y(n - 1)","y(n) = x(n)","y(n) + y(n - 1) + y(n + 3) = 0");
        electronics_single_correct.add(questions38);
        Questions questions39 = new Questions(2,"Which of the following oscillators is suitable for frequencies in the range of mega hertz?","C","RC phase shift","Wien bridge","Hartley","None of the above");
        electronics_single_correct.add(questions39);
        Questions questions40 = new Questions(3,"In a BJT circuit a pnp transistor is replaced by npn transistor. To analyse the new circuit","D","all calculations done earlier have to be repeated","replace all calculated voltages by reverse values","replace all calculated currents by reverse values","replace all calculated voltages and currents by reverse values");
        electronics_single_correct.add(questions40);
        Questions questions41 = new Questions(4,"A broadside array consisting of 200 cm wavelength with 10 half-wave dipole spacing 10 cm. And if each array element feeding with 1 amp. current and operating at same frequency then find the half power beamwidth","B","4°","2°","10°","15");
        electronics_single_correct.add(questions41);
        Questions questions42 = new Questions(5,"VSB modulation is preferred in TV because","A","it reduces the bandwidth requirement to half","it avoids phase distortion at low frequencies","it results in better reception","none of the above");
        electronics_single_correct.add(questions42);
        Questions questions43 = new Questions(6,"A woofer should be fed from the input through a","A","low pass filter","high pass filter","band pass filter","band stop filter");
        electronics_single_correct.add(questions43);
        Questions questions44 = new Questions(7,"In a superheterodyne receiver","A","the IF stage has better selectivity than RF stage ","the RF stage has better selectivity than IF stage","the RF stage has same selectivity than IF stage","none of the above");
        electronics_single_correct.add(questions44);
        Questions questions45 = new Questions(8,"MICR stands for","A","Magnetic Ink Chart Receipt","Magnetic Ink Character Recognition","Magnetic Ink Chart Recognition","Magnetic Ink Character Receipt");
        electronics_single_correct.add(questions45);
        Questions questions46 = new Questions(9,"In a minimum function","A","the degree of numerator and denominator are equal","the degree of numerator and denominator are unequal","the degree of numerator is one more than degree of denominator","the degree of numerator is one less than degree of denominator");
        electronics_single_correct.add(questions46);
        Questions questions47 = new Questions(10,"In a klystron amplifier the input cavity is called","A","buncher","catcher","Pierce gun","collector");
        electronics_single_correct.add(questions47);
        Questions questions48 = new Questions(1,"Formative time lag depends on the mechanism of the avalanche growth in gap. The formative time lag is usually","A","Much shorter than the statistical time lag","Much greater than the statistical time lag","Equal to the statistical time lag","None of these");
        electrical_single_correct.add(questions48);
        Questions questions49 = new Questions(2,"Field in case of rod gaps and sphere gaps are","C","Uniform, uniform","Uniform, non-uniform","Non-uniform, uniform","Non-uniform, non-uniform");
        electrical_single_correct.add(questions49);
        Questions questions50 = new Questions(3,"The increase in liquid hydrostatic pressure","A","Increases the breakdown strength","Decreases the breakdown strength","Does not affect the breakdown strength","None of these");
        electrical_single_correct.add(questions50);
        Questions questions51 = new Questions(4,"What is the purpose of bedding on the underground cables?","D","To avoid leakage of current.","To protect the sheath against corrosion.","To protect the sheath from mechanical injury due to armouring.","Both (b) and (c)");
        electrical_single_correct.add(questions51);
        Questions questions52 = new Questions(5,"What is the main drawback of using paper as the insulating material?","A","Is hygroscopic","Has poor dielectric strength","Has a very low insulation resistivity","Has high capacitance");
        electrical_single_correct.add(questions52);
        Questions questions53 = new Questions(6,"The most suitable location for the power factor improvement device is","D","Near the electrical appliance which is responsible for the poor power factor.","At the sending end.","At the receiving end in case of transmission lines.","Both (a) and (c).");
        electrical_single_correct.add(questions53);
        Questions questions54 = new Questions(7,"Which tariff is used by the small commercial consumers?","B","Maximum demand tariff","Block rate tariff","Three part tariff","Two part tariff");
        electrical_single_correct.add(questions54);
        Questions questions55 = new Questions(8," A module in a solar panel refers to_______________","C","series arrangement of solar cells.","parallel arrangement of solar cells.","series and parallel arrangement of solar cells.","None of the above");
        electrical_single_correct.add(questions55);
        Questions questions56 = new Questions(9,"What is the advantage of HRC fuses over Rewirable fuses?","D","High speed operation","High rupturing capacity"," No ageing effect.","All of the above.");
        electrical_single_correct.add(questions56);
        Questions questions57 = new Questions(10,"Which test is also called as back to back test?","B","Retardation test","Sumpner’s test","Field test","Voltage drop test");
        electrical_single_correct.add(questions57);

        //logic
        Questions questions1 = new Questions(1,"A is son of C while C and Q are the sisters to one another. Z is the mother of Q. If P is the son of Z, Which one of the following statements is correct ?" ,
                "B", "Q is the grandfather of A", "P is the maternal uncle of A",
                "P is the cousin of A",
                "Z is the brother of C");
        logic_single_correct.add(questions1);

        Questions questions2 = new Questions(2,"In a family there are several brothers and sisters. Every 2 boys have brothers as many as sisters and each girl has 2 brothers less than twice as many brothers as sisters.\n" +
                "Now find the number of boys and girls." ,
                "A", "8 , 6 ", "6 , 4",
                "6 , 8",
                "12 , 10");
        logic_single_correct.add(questions2);

        Questions questions3 = new Questions(3,"If you are given that\n" +
                "ENEMY = 2 * LINK\n" +
                "and\n" +
                "LINK + 2 = HELL\n" +
                "\n" +
                "Can you find out?\n" +
                "TAXI + HELL = ?\n" +
                "\n" +
                "For your convenience, we have also given you the following options:" ,
                "A", "HEAVEN", "STIGMA",
                "MORALE",
                "PORTAL");
        logic_single_correct.add(questions3);

        Questions questions4 = new Questions(4,"There are 25 horses among which you need to find out the fastest 3 horses. You can conduct race among\n" +
                "   at most 5 to find out their relative speed. At no point you can find out the actual speed of the horse in a race.\n" +
                "   Find out how many races are required to get the top 3 horses." ,
                "B", "5", "7",
                "9",
                "11");
        logic_single_correct.add(questions4);

        Questions questions5 = new Questions(5,"Given two identical wires, each of which takes an hour to burn. We have matchstickswith us.\n" +
                "    The wires burn non-uniformly. So, for example, the two halves of a wire might burn in 10 minute and 50 minutes\n" +
                "    respectively\n" +
                "    which time can we measure using these two wires?" ,
                "D", "15", "30",
                "45",
                "all of them");
        logic_single_correct.add(questions5);

        Questions questions6 = new Questions(6,"Alok has three daughters. His friend Shyam wants to know the ages of his daughters. Alok gives him first hint.\n" +
                "\n" +
                "1) The product of their ages is 72.\n" +
                "Shyam says this is not enough information Alok gives him a second hint.\n" +
                "2) The sum of their ages is equal to my house number.\n" +
                "Shyam goes out and look at the house number and tells “I still do not have enough information to determine the ages”.\n" +
                "Alok admits that Shyam can not guess and gives him the third hint\n" +
                "3) The oldest of the girls likes strawbarry ice-cream.\n" +
                "Shyam is able to guess after the third hint. Can you guess what are the ages of three daughters?" ,
                "B", "3,4,6", "3,3,8",
                "2,6,6",
                "2,4,9");
        logic_single_correct.add(questions6);

        Questions questions7 = new Questions(7,"100 people stand in a circle in order 1 to 100. No. 1 has a sword. He kills the next person (i.e. No. 2) \n" +
                "    and gives the sword to the next living person (i.e. No. 3). All people do the same until only 1 survives.\n" +
                "    Which number survives to the end?" ,
                "C", "64", "81",
                "73",
                "1");
        logic_single_correct.add(questions7);

        Questions questions8 = new Questions(8,"Three ants are sitting at the three corners of an equilateral triangle. Each ant randomly picks a direction \n" +
                "    and starts to move along the edge of the triangle. What is the probability that none of the ants collide?" ,
                "D", "0.5", "0.2",
                "0.1",
                "0.25");
        logic_single_correct.add(questions8);

        Questions questions9 = new Questions(9,"Andrew, Britney and Carol race each other in a 100 meters race. All of them run at a constant speed throughout the race.\n" +
                "\n" +
                "Andrew beats Britney by 20 meters.\n" +
                "Britney beats Carol by 20 meters.\n" +
                "\n" +
                "By how many meters does Andrew beat Carol?" ,
                "C", "40", "56",
                "64",
                "72");
        logic_single_correct.add(questions9);


        //Mechanical
        Questions questions10 = new Questions(1,"What is meant by Aesthetics?" ,
                "A", "Interaction between man machine working environment", "Appearance of the product",
                "Both a. and b.",
                "None of the above");
        logic_single_correct.add(questions10);
        Questions questions11 = new Questions(2,"The radial force in rotodynamic machine, which is developed by rate of change of momentum in radial velocity, is taken care by__________." ,
                "A", "journal bearing", "radial thrust bearing",
                "both a. and b.",
                "none of the above");
        logic_single_correct.add(questions11);
        Questions questions12 = new Questions(3,"_______________ components of fluid velocity have the responsibility of the transportation of fluid through the machine." ,
                "C", "Radial and tangential", "Tangential and axial",
                "Radial and axial",
                "None of the existing");
        logic_single_correct.add(questions12);
        Questions questions13 = new Questions(4,"The characteristic that provides an output with respect to the relation with the input is called as ___________" ,
                "B", "calibration of a system", "response of a system",
                "characteristic relation of a system",
                "instrumentation of a system");
        logic_single_correct.add(questions13);
        Questions questions14 = new Questions(5,"The process of establishment of a relationship between the input to the instrument and output from the instrument is called as_____" ,
                "D", "40", "56",
                "64",
                "72");
        logic_single_correct.add(questions14);


        //personality
        Questions questions15 = new Questions(1, "Select the option with which you agree the most.",
                "",
                "I get a lot of invitations from friends.",
                "I have an open mind towards what other people might say.",
                "Both A and B",
                "Neither A nor B");
        personality_single_correct.add(questions15);

        Questions questions16 = new Questions(2, "Select the option with which you agree the most.",
                "",
                "I smile at everyone I see.",
                "I always ask for a bill (cash memo) when I buy stuff.",
                "Both A and B",
                "Neither A nor B");
        personality_single_correct.add(questions16);

        Questions questions17 = new Questions(3, "Select the option with which you \n" +
                "agree the most.",
                "",
                "I often regret my decisions.",
                "Doing multiple things at one time can be fun.",
                "Both A and B",
                "Neither A nor B");
        personality_single_correct.add(questions17);

        Questions questions18 = new Questions(4, "Select the option with which you agree the most.",
                "",
                "I make a schedule to study for my exams.",
                "I am uncomfortable interacting with the opposite sex.",
                "Both A and B",
                "Neither A nor B");
        personality_single_correct.add(questions18);

        Questions questions19 = new Questions(5, "Select the \n" +
                "option with which you agree the most.",
                "",
                "I like to decorate my room.",
                "I am very polite and respectful to everyone I meet.",
                "Both A and B",
                "Neither A nor B");
        personality_single_correct.add(questions19);
        Questions questions61 = new Questions(1,
                "Determine output:\n" +
                        "void main()\n" +
                        "{\n" +
                        "int const *p=5;\n" +
                        "printf(\"%d\", ++(*p));\n" +
                        "}\n",
                "D",
                "6", "5", "Garbage Value", "Compiler Error");
        Basic_C_single_correct.add(questions61);
        Questions questions62 = new Questions(2,"Determine Output:\n" +
                "\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "char s[]=\"man\";\n" +
                "int i;\n" +
                "for(i=0; s[i]; i++)\n" +
                "printf(\"%c%c%c%c \", s[i], *(s+i), *(i+s), i[s]);\n" +
                "}\n" +
                "\n",
                "D",
                "mmm nnn aaa","mmmm nnnn aaaa","Compiler Error", "None of These");
        Basic_C_single_correct.add(questions62);

        Questions questions63= new Questions(3,
                "Determine Output:\n" +
                "void main()\n" +
                "{\n" +
                "float me = 1.1;\n" +
                "double you = 1.1;\n" +
                "if(me==you)\n" +
                "printf(\"I hate coding\");\n" +
                "else\n" +
                "printf(\"I love coding\");\n" +
                "}\n",
                "B",
                "I hate coding","I love coding","Error","None of These");
        Basic_C_single_correct.add(questions63);

        Questions questions64= new Questions(4,"What command is used to count the total number of lines," +
                " words, and characters contained in a file?",
                "C",
                "countw","wcount","wc", "\n" +
                "count p");
        Basic_C_single_correct.add(questions64);

        Questions questions65= new Questions(5,"\n" +
                "With respect to a network interface card, the term 10/100 refers to",
                "C",
                "protocol speed", "a fiber speed","megabits per seconds","minimum and maximum server speed");
        Basic_C_single_correct.add(questions65);

        Questions questions66= new Questions(6,"By using which method sorting is not possible?",
                "C",
                "Insertion","Selection","Deletion","Exchange");
        Basic_C_single_correct.add(questions66);

        Questions questions67= new Questions(7,"C programming : Match the following:\n" +
                "a. calloc( )  ------- i. Frees previouslyallocated space.\n" +
                "b. free( ) ----------- ii. Modifiespreviouslyallocated space.\n" +
                "c. malloc( ) ------- iii. Allocates spacefor array.\n" +
                "d. realloc( ) ------- iv. Allocatesrequested size ofspace.\n",
                "A",
                "a-iii, b-i, c –iv, d -ii","a-iii, b-ii, c –i, d -iv","a-iii, b-iv, c –i, d -ii","a-iv, b-ii, c –iii, d -i");
        Basic_C_single_correct.add(questions67);

        Questions questions68= new Questions(8,"Which of the tool is used to compile java code ?",
                "C",
                "java","jar", "javac","javadoc");
        Basic_C_single_correct.add(questions68);

        Questions questions69= new Questions(9,"The mouse pointer moves erratically," +
                " what is the possible cause? The mouse",
                "A",
                "ball is dirty","is not connected","driver is not installed properly",
                "has an incorrect IRQ setting");
        Basic_C_single_correct.add(questions69);

        Questions questions70= new Questions(10,"Multiplication of 1112 by 1012 is","B","1100112","1000112","1111002","0001012");
        Basic_C_single_correct.add(questions70);


        //Civil
        Questions questions71= new Questions(11,"Pick up the polymineralic rock from the following:",
                "D",
                "Quartz sand","Pure gypsum","Magnesite","Granite");
        civil_single_correct.add(questions71);

        Questions questions72= new Questions(12,"Lacquer paints\n",
                "D",
                "are generally applied on structural steel",
                "are less durable as compared to enamel paints",
                "consist of resin and nitro-cellulose",
                "all the above");
        civil_single_correct.add(questions72);

        Questions questions73= new Questions(13,"Most economical section of a circular channel for maximum discharge",
                "D",
                "depth of water = 0.95 diameter of circular section",
                "hydraulic mean depth = 0.286 diameter of circular section",
                "wetted perimeter = 2.6 diameter of circular section","all the above");
        civil_single_correct.add(questions73);

        Questions questions74= new Questions(14,"For quality control of Portland cement, the test essentially done is",
                "D",
                "setting time","soundness","tensile strength","all the above.");
        civil_single_correct.add(questions74);

        Questions questions75= new Questions(15,"For given water content, workability decreases if the concrete aggregates" +
                " contain an excess of",
                "D",
                "thin particles","flat particles","elongated particles", "all the above");
        civil_single_correct.add(questions75);

        Questions questions76= new Questions(16,"The technique for establishing and maintaining priorities among the various" +
                " jobs of a project, is known",
                "B",
                "Event flow scheduling technique", "Critical ratio scheduling","Slotting technique for scheduling",
                "Short interval scheduling");
        civil_single_correct.add(questions76);

        Questions questions77= new Questions(17,"Frederick W. Taylor introduced a system of working known as",
                "C",
                "line organisation","line and staff organisation","functional organisation",
                "effective organisation");
        civil_single_correct.add(questions77);

        Questions questions78= new Questions(18,"The beach is built:",
                "A",
                "with largest material locally available to the waves",
                "with large material locally available to the waves",
                "with fine material locally available to the waves",
                "with finest material locally available to the waves");
        civil_single_correct.add(questions78);

        Questions questions79= new Questions(19,"Design of horizontal and vertical alignments, super-elevation," +
                " sight distance and grades, is worst affected by",
                "D",
                "width of the vehicle", "length of the vehicle","height of the vehicle","speed of the vehicle");
        civil_single_correct.add(questions79);

        Questions questions80= new Questions(20,"Thickness of a pavement may be reduced considerably by",
                "D",
                "compaction of soil","stabilisation of soil","drainage of soil",
                "combination of all the above");
        civil_single_correct.add(questions80);


        //CSE

        Questions questions81= new Questions(21,"Every weak entity set can be converted into a strong" +
                " entityset by:\n","B","using generalisation","adding appropriate attributes",
                "using aggregation","none of the above\n");
        CSE_single_correct.add(questions81);

        Questions questions82= new Questions(22,"Suppose that a process is in 'Blocked' state waiting for some I/O service. when the service is\n" +
                "    completed, it goes to the\n",
                "B",
                "RUNNING state","READY state", "SUSPENDED state","TERMINATED state");
        CSE_single_correct.add(questions82);

        Questions questions83= new Questions(23,"Herder node is used as sentinel in.....\n",
                "D",
                "Queues","Stacks","Graphs","Binary Tree");
        CSE_single_correct.add(questions83);

        Questions questions84= new Questions(24,"You are given two singly linked lists with heads head_ref1 and head_ref2 respectively. What does\n" +
                "    the following function do?\n" +
                "\tint myFunc(Node* head_ref1, Node* head_ref2)  {\n" +
                "    \tNode *pointer1 = head_ref1, *pointer2 = head_ref2;\n" +
                "    \twhile (pointer1 != pointer2)  {\n" +
                "        \t\tpointer1 = pointer1?pointer1->next:head_ref2;\n" +
                "        \t\tpointer2 = pointer2?pointer2->next:head_ref1;\n" +
                "    \t\t}\n" +
                "    \t\treturn pointer1?pointer1->data:-1;\n" +
                "    \t}\n",
                "B",
                "Merging the two linked lists","Finding the merging point of the two linked lists",
                "Swapping nodes of the two linked lists","The program runs in an infinite loop");
        CSE_single_correct.add(questions84);

        Questions questions85= new Questions(25,"What is the number of binary search trees with 20 nodes with elements 1, 2, 3,…..20" +
                " such that the root of tree is 12 and the root of left subtree is 7?\n",
                "D",
                "2634240", "1243561","350016","2642640");
        CSE_single_correct.add(questions85);
    }


    public List<Questions> getEnglish_single_correct() {
        return english_single_correct;
    }

    public List<Questions> getElectronics_single_correct() {
        return electronics_single_correct;
    }

    public List<Questions> getElectrical_single_correct() {
        return electrical_single_correct;
    }

    public List<Questions> getLogic_single_correct() {
        return logic_single_correct;
    }

    public List<Questions> getMechanical_single_correct() {
        return mechanical_single_correct;
    }

    public List<Questions> getPersonality_single_correct() {
        return personality_single_correct;
    }

    public List<Questions> getBasic_C_single_correct() {
        return Basic_C_single_correct;
    }

    public List<Questions> getCivil_single_correct() {
        return civil_single_correct;
    }

    public List<Questions> getCSE_single_correct() {
        return CSE_single_correct;
    }
}
