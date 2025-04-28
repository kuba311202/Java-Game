import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainFrame implements ActionListener {


    private JFrame frame;
    private JLabel chooseMenu;
    private JLabel chooseSpellsMenu;
    private JButton attackMonster1;
    protected JButton attack;
    protected JButton defend;
    protected JButton spells;
    protected JButton items;
    protected JLabel playerHealth;
    protected JLabel playerMana;
    protected JLabel monster1Health;
    protected JLabel monster1Mana;
    protected JLabel Graphic1;
    private int screenWidth;
    private int screenHeight;
    private boolean playerTurn;
    private Character Player;
    private Container panel;
    private Container mainMenuPanel;
    private Enemy Monster1;
    private SpringLayout layout;
    private SpringLayout attackLayout;
    private Container pathsPanel;
    private SpringLayout pathsLayout;
    private boolean end = false;
    private ActionListener pathListener;
    private JButton path1;
    private JButton path2;
    private JButton path3;
    private GeneralEncounter generalEncounter1;
    private FightEnemy fight1;


    public MainFrame() {
        initialize();
    }
    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Game");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);

        Rectangle r = frame.getBounds();
        screenWidth = r.width;
        screenHeight = r.height;

        mainMenuPanel = frame.getContentPane();

        ImageIcon background = new ImageIcon("../Gra/images/MainMenu.jpg");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel back = new JLabel(background);
        back.setLayout(null);
        back.setBounds(0, 0, screenWidth, screenHeight);

        JButton start = new JButton();
        start.setPreferredSize(new Dimension(300, 200));
        start.setIcon(new ImageIcon("../Gra/images/attackButton.png"));

        JButton settings = new JButton();
        settings.setPreferredSize(new Dimension(300, 200));
        settings.setIcon(new ImageIcon("../Gra/images/defendButton.png"));

        JButton exit = new JButton();
        exit.setPreferredSize(new Dimension(300, 200));
        exit.setIcon(new ImageIcon("../Gra/images/spellButton.png"));

        SpringLayout mainMenuLayout = new SpringLayout();
        assert mainMenuPanel != null;
        mainMenuPanel.setLayout(mainMenuLayout);


        mainMenuPanel.add(start);
        mainMenuPanel.add(settings);
        mainMenuPanel.add(exit);

        mainMenuPanel.add(back);

        frame.setContentPane(mainMenuPanel);


        mainMenuLayout.putConstraint(SpringLayout.NORTH, start, 150, SpringLayout.NORTH, mainMenuPanel);
        mainMenuLayout.putConstraint(SpringLayout.NORTH, settings, 50, SpringLayout.SOUTH, start);
        mainMenuLayout.putConstraint(SpringLayout.NORTH, exit, 50, SpringLayout.SOUTH, settings);

        mainMenuLayout.putConstraint(SpringLayout.EAST, start, -760, SpringLayout.EAST, mainMenuPanel);
        mainMenuLayout.putConstraint(SpringLayout.EAST, settings, -760, SpringLayout.EAST, mainMenuPanel);
        mainMenuLayout.putConstraint(SpringLayout.EAST, exit, -760, SpringLayout.EAST, mainMenuPanel);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back.setVisible(false);
                mainMenuPanel.setVisible(false);
                start.setVisible(false);
                settings.setVisible(false);
                exit.setVisible(false);

                Player = new Character("Warrior");
                Fireball fireball = new Fireball();
                Player.AddSpell(fireball);
                Lesser_heal lesser_heal = new Lesser_heal();
                Player.AddSpell(lesser_heal);


                generalEncounter1 = new GeneralEncounter("Fight", "1");
                renderPaths(generalEncounter1);

            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


    }
    public void updateBars(FightEnemy Fight){
        playerHealth.setText(Double.toString(Player.GetHP()));
        playerMana.setText(Double.toString(Player.GetMana()));
        if(Fight.slot1!=null){
            monster1Health.setText(Double.toString(Fight.slot1.GetHP()));
            monster1Mana.setText(Double.toString(Fight.slot1.GetMana()));
        }
    }


    public void checkTurn(FightEnemy Fight){
        if(playerTurn){
            updateBars(Fight);
            pTurn();
        }else{
            updateBars(Fight);
            PcTurn();
        }
    }


    public void show(){
        frame.setVisible(true);
    }


    public void renderPaths(GeneralEncounter generalEncounter){
        ImageIcon background = new ImageIcon("../Gra/images/BackgroundPaths.png");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel back = new JLabel(background);
        back.setLayout(null);
        back.setBounds(0, 0, screenWidth, screenHeight);

        pathsPanel = new Container();
        frame.setContentPane(pathsPanel);

        path1 = new JButton();
        path1.setPreferredSize(new Dimension(300, 500));
        path1.setIcon(new ImageIcon("../Gra/images/attackButton.png"));

        path2 = new JButton();
        path2.setPreferredSize(new Dimension(300, 500));
        path2.setIcon(new ImageIcon("../Gra/images/defendButton.png"));

        path3 = new JButton();
        path3.setPreferredSize(new Dimension(300, 500));
        path3.setIcon(new ImageIcon("../Gra/images/spellButton.png"));

        pathsLayout = new SpringLayout();
        assert pathsPanel != null;
        pathsPanel.setLayout(pathsLayout);


        pathsPanel.add(path1);
        pathsPanel.add(path2);
        pathsPanel.add(path3);

        pathsPanel.add(back);

        pathsLayout.putConstraint(SpringLayout.NORTH, path1, 350, SpringLayout.NORTH, pathsPanel);
        pathsLayout.putConstraint(SpringLayout.NORTH, path2, 350, SpringLayout.NORTH, pathsPanel);
        pathsLayout.putConstraint(SpringLayout.NORTH, path3, 350, SpringLayout.NORTH, pathsPanel);

        pathsLayout.putConstraint(SpringLayout.EAST, path1, 550, SpringLayout.WEST, pathsPanel);
        pathsLayout.putConstraint(SpringLayout.EAST, path2, 550, SpringLayout.EAST, path1);
        pathsLayout.putConstraint(SpringLayout.EAST, path3, 550, SpringLayout.EAST, path2);


        pathListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(generalEncounter.Type, "Fight")){
                    fight1 = new FightEnemy(generalEncounter.Number);
                    back.setVisible(false);
                    pathsPanel.setVisible(false);
                    path1.setVisible(false);
                    path2.setVisible(false);
                    path3.setVisible(false);
                    renderFight(fight1);
                }
                pTurn();
            }
        };
        path1.addActionListener(pathListener);
    }

    public void renderFight(FightEnemy Fight){
        path1.removeActionListener(pathListener);
        ImageIcon background = new ImageIcon("../Gra/images/Background.png");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel back = new JLabel(background);
        back.setLayout(null);
        back.setBounds(0, 0, screenWidth, screenHeight);

        panel = new Container();
        frame.setContentPane(panel);

        attack = new JButton();
        attack.setPreferredSize(new Dimension(230, 60));
        attack.setIcon(new ImageIcon("../Gra/images/attackButton.png"));

        defend = new JButton();
        defend.setPreferredSize(new Dimension(230, 60));
        defend.setIcon(new ImageIcon("../Gra/images/defendButton.png"));

        spells = new JButton();
        spells.setPreferredSize(new Dimension(230, 60));
        spells.setIcon(new ImageIcon("../Gra/images/spellButton.png"));

        items = new JButton();
        items.setPreferredSize(new Dimension(230, 60));
        items.setIcon(new ImageIcon("../Gra/images/itemButton.png"));


        playerHealth = new JLabel();
        playerHealth.setPreferredSize(new Dimension(100,30));
        playerHealth.setIcon(new ImageIcon("../Gra/images/healthBar.png"));
        playerHealth.setText(Double.toString(Player.GetHP()));
        playerHealth.setVerticalTextPosition(JLabel.CENTER);
        playerHealth.setHorizontalTextPosition(JLabel.CENTER);

        playerMana = new JLabel();
        playerMana.setPreferredSize(new Dimension(100,30));
        playerMana.setIcon(new ImageIcon("../Gra/images/manaBar.png"));
        playerMana.setText(Double.toString(Player.GetMana()));
        playerMana.setVerticalTextPosition(JLabel.CENTER);
        playerMana.setHorizontalTextPosition(JLabel.CENTER);

        Graphic1 = new JLabel();
        Graphic1.setIcon(new ImageIcon("../Gra/images/Boar.png"));

        monster1Health = new JLabel();
        monster1Health.setPreferredSize(new Dimension(100,30));
        monster1Health.setIcon(new ImageIcon("../Gra/images/healthBar.png"));
        monster1Health.setText(Double.toString(Fight.slot1.GetHP()));
        monster1Health.setVerticalTextPosition(JLabel.CENTER);
        monster1Health.setHorizontalTextPosition(JLabel.CENTER);

        monster1Mana = new JLabel();
        monster1Mana.setPreferredSize(new Dimension(100,30));
        monster1Mana.setIcon(new ImageIcon("../Gra/images/manaBar.png"));
        monster1Mana.setText(Double.toString(Fight.slot1.GetMana()));
        monster1Mana.setVerticalTextPosition(JLabel.CENTER);
        monster1Mana.setHorizontalTextPosition(JLabel.CENTER);


        chooseMenu = new JLabel();
        chooseMenu.setPreferredSize(new Dimension(1650,325));
        chooseMenu.setIcon(new ImageIcon("../Gra/images/chooseMenu.png"));

        attackMonster1 = new JButton();
        attackMonster1.setPreferredSize(new Dimension(200,60));
        attackMonster1.setText(Fight.slot1.name);
        attackMonster1.setVerticalTextPosition(JMenuItem.CENTER);
        attackMonster1.setHorizontalTextPosition(JMenuItem.CENTER);

        chooseSpellsMenu = new JLabel();
        chooseSpellsMenu.setPreferredSize(new Dimension(1650,325));
        chooseSpellsMenu.setIcon(new ImageIcon("../Gra/images/chooseMenu.png"));

        JButton Spell0 = new JButton();
        Spell0.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[0]!=null) {
            Spell0.setText(Player.spellsLibrary[0].name);
        }else {
            Spell0.setVisible(false);
        }
        Spell0.setVerticalTextPosition(JMenuItem.CENTER);
        Spell0.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell1 = new JButton();
        Spell1.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[1]!=null) {
            Spell1.setText(Player.spellsLibrary[1].name);
        }else {
            Spell1.setVisible(false);
        }
        Spell1.setVerticalTextPosition(JMenuItem.CENTER);
        Spell1.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell2 = new JButton();
        Spell2.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[2]!=null) {
            Spell2.setText(Player.spellsLibrary[2].name);
        }else {
            Spell2.setVisible(false);
        }
        Spell2.setVerticalTextPosition(JMenuItem.CENTER);
        Spell2.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell3 = new JButton();
        Spell3.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[3]!=null) {
            Spell3.setText(Player.spellsLibrary[3].name);
        }else {
            Spell3.setVisible(false);
        }
        Spell3.setVerticalTextPosition(JMenuItem.CENTER);
        Spell3.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell4 = new JButton();
        Spell4.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[4]!=null) {
            Spell4.setText(Player.spellsLibrary[4].name);
        }else {
            Spell4.setVisible(false);
        }
        Spell4.setVerticalTextPosition(JMenuItem.CENTER);
        Spell4.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell5 = new JButton();
        Spell5.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[5]!=null) {
            Spell0.setText(Player.spellsLibrary[5].name);
        }else {
            Spell0.setVisible(false);
        }
        Spell5.setVerticalTextPosition(JMenuItem.CENTER);
        Spell5.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell6 = new JButton();
        Spell6.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[6]!=null) {
            Spell6.setText(Player.spellsLibrary[6].name);
        }else {
            Spell6.setVisible(false);
        }
        Spell6.setVerticalTextPosition(JMenuItem.CENTER);
        Spell6.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell7 = new JButton();
        Spell7.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[7]!=null) {
            Spell7.setText(Player.spellsLibrary[7].name);
        }else {
            Spell7.setVisible(false);
        }
        Spell7.setVerticalTextPosition(JMenuItem.CENTER);
        Spell7.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell8 = new JButton();
        Spell8.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[8]!=null) {
            Spell8.setText(Player.spellsLibrary[8].name);
        }else {
            Spell8.setVisible(false);
        }
        Spell8.setVerticalTextPosition(JMenuItem.CENTER);
        Spell8.setHorizontalTextPosition(JMenuItem.CENTER);

        JButton Spell9 = new JButton();
        Spell9.setPreferredSize(new Dimension(200,60));
        if(Player.spellsLibrary[9]!=null) {
            Spell9.setText(Player.spellsLibrary[9].name);
        }else {
            Spell9.setVisible(false);
        }
        Spell9.setVerticalTextPosition(JMenuItem.CENTER);
        Spell9.setHorizontalTextPosition(JMenuItem.CENTER);

        attackLayout = new SpringLayout();
        assert chooseMenu != null;
        chooseMenu.setLayout(attackLayout);

        attackLayout.putConstraint(SpringLayout.NORTH, attackMonster1, 10, SpringLayout.NORTH, chooseMenu);


        attackLayout.putConstraint(SpringLayout.WEST, attackMonster1, 10, SpringLayout.WEST, chooseMenu);

        chooseMenu.setVisible(false);
        chooseSpellsMenu.setVisible(false);

        layout = new SpringLayout();
        assert panel != null;
        panel.setLayout(layout);

        panel.add(attack);
        panel.add(defend);
        panel.add(spells);
        panel.add(items);
        panel.add(playerHealth);
        panel.add(playerMana);
        panel.add(Graphic1);
        panel.add(monster1Health);
        panel.add(monster1Mana);
        panel.add(chooseMenu);

        panel.add(back);

        chooseMenu.add(attackMonster1);



        layout.putConstraint(SpringLayout.SOUTH, items, -30, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.SOUTH, spells, -20, SpringLayout.NORTH, items);
        layout.putConstraint(SpringLayout.SOUTH, defend, -20, SpringLayout.NORTH, spells);
        layout.putConstraint(SpringLayout.SOUTH, attack, -20, SpringLayout.NORTH, defend);
        layout.putConstraint(SpringLayout.SOUTH, playerHealth, -400, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.NORTH, playerMana, 10, SpringLayout.SOUTH, playerHealth);
        layout.putConstraint(SpringLayout.SOUTH, Graphic1, -340, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.SOUTH, monster1Health, -30, SpringLayout.NORTH, Graphic1);
        layout.putConstraint(SpringLayout.SOUTH, monster1Mana, 5, SpringLayout.NORTH, Graphic1);
        layout.putConstraint(SpringLayout.SOUTH, chooseMenu, -5, SpringLayout.SOUTH, panel);



        layout.putConstraint(SpringLayout.WEST, defend, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, attack, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, spells, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, items, 25, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.EAST, playerHealth, -25, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.EAST, playerMana, -25, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.EAST, Graphic1, -800, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.WEST, monster1Health, -30, SpringLayout.EAST, Graphic1);
        layout.putConstraint(SpringLayout.WEST, monster1Mana, -30, SpringLayout.EAST, Graphic1);
        layout.putConstraint(SpringLayout.WEST, chooseMenu, 10, SpringLayout.EAST, attack);


        Monster1 = Fight.slot1;
        playerTurn=true;
        attack.addActionListener(new attackListener() {});
        defend.addActionListener(new defendListener() {});
        spells.addActionListener(new spellsListener());
        items.addActionListener(new itemsListener());
    }

    public class attackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            chooseMenu.setVisible(true);
            attackMonster1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Monster1.SetHp(-Player.attack + (Monster1.armor*0.5));
                    playerTurn = false;
                    checkTurn(fight1);
                }
            });
        }
    }

    public class defendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Player.SetArmor(5);
            Player.SetDefend(true);
            playerTurn = false;
            checkTurn(fight1);
        }
    }

    public class spellsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Player.spellsLibrary[0].Damage(Monster1, Player);
            System.out.println(Player.spellsLibrary[0].amount);
            playerTurn = false;
            checkTurn(fight1);
        }
    }

    public class itemsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerTurn = false;
            checkTurn(fight1);
        }
    }

    public void pTurn(){

        if (Player.GetHP() <= 0) {
                    System.out.println("Przgrałes");
                }
                if(Player.defend){
                    Player.SetArmor(-5);
                    Player.SetDefend(false);
                }
    }

    public void PcTurn(){
        if (Monster1.GetHP() <= 0) {
            System.out.println("Wygrales");
        }
        Player.SetHp(-Monster1.attack + (Player.armor*0.5));
        playerTurn=true;
        checkTurn(fight1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

