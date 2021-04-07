package ui;

import exception.IndexException;
import exception.UniqueTitleException;
import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// CITATION: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//           ListDemo
// Represents the GUI of LibraryApp
public class LibraryGUI extends JPanel implements ListSelectionListener {
    private static final String JSON_STORE = "./data/library.json";
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JList list;
    private DefaultListModel listModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private static final String titleString = "Title: ";
    private static final String authorString = "Author: ";
    private static final String bodyString = "Body: ";

    private JPanel textPane;
    private JPanel buttonPane;
    private GroupLayout layout;

    private JLabel titleLabel;
    private JTextField titleField;

    private JLabel authorLabel;
    private JTextField authorField;

    private JLabel bodyLabel;
    private JTextArea bodyArea;
    private JScrollPane bodyScrollArea;

    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;

    private String addSound = "./data/addsound.wav";
    private String removeSound = "./data/removesound.wav";

    // EFFECTS: initializes the application and GUI
    public LibraryGUI() {
        super(new BorderLayout());
        init();
        initLabelField();
        initButtons();
        addList();
        addButtons();
        addTextFields();
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    private void init() {
        library = new Library();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes the text fields
    private void initLabelField() {
        titleLabel = new JLabel();
        titleLabel.setText(titleString);
        titleField = new JTextField(20);

        authorLabel = new JLabel();
        authorLabel.setText(authorString);
        authorField = new JTextField(20);

        bodyLabel = new JLabel();
        bodyLabel.setText(bodyString);
        bodyArea = new JTextArea();
        bodyArea.setColumns(40);
        bodyArea.setLineWrap(true);
        bodyArea.setRows(10);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(true);
        bodyScrollArea = new JScrollPane(bodyArea);
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu buttons
    private void initButtons() {
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(true);

        removeButton = new JButton(removeString);
        RemoveListener removeListener = new RemoveListener(removeButton);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(false);

        saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);

        loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);
    }

    // CITATION: https://docs.oracle.com/javase/8/docs/api/javax/swing/GroupLayout.html
    // MODIFIES: this
    // EFFECTS: creates the panel for text fields that are grouped
    private void addTextFields() {
        textPane = new JPanel();
        layout = new GroupLayout(textPane);
        textPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();

        horizontalGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleLabel).addComponent(authorLabel).addComponent(bodyLabel));
        horizontalGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleField).addComponent(authorField).addComponent(bodyScrollArea));
        layout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();

        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(titleLabel).addComponent(titleField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(authorLabel).addComponent(authorField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(bodyLabel).addComponent(bodyScrollArea));

        layout.setVerticalGroup(verticalGroup);

        add(textPane, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: creates the menu button pane
    private void addButtons() {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: creates the scroll list pain
    private void addList() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: updates the usability of removeButton based on list selection
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);

            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    // represents the handler of the addButton
    private class AddListener implements ActionListener {
        private JButton button;

        // EFFECTS: constructs the button listener
        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        // MODIFIES: LibraryGUI.this
        // EFFECTS: handles the adding of a book to the GUI and library
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String author = authorField.getText();
            String body = bodyArea.getText();

            try {
                library.addBook(new Book(title, author, body));
            } catch (UniqueTitleException uniqueTitleException) {
                Toolkit.getDefaultToolkit().beep();
                titleField.requestFocusInWindow();
                titleField.selectAll();
                return;
            }

            listModel.addElement(titleField.getText());

            titleField.requestFocusInWindow();
            titleField.setText("");
            authorField.setText("");
            bodyArea.setText("");

            int index = list.getSelectedIndex();
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

            AudioPlayer.playSound(addSound);
        }
    }

    // represents the handler of the removeButton
    private class RemoveListener implements ActionListener {
        private JButton button;

        // EFFECTS: constructs the button listener
        public RemoveListener(JButton button) {
            this.button = button;
        }

        @Override
        // MODIFIES: LibraryGUI.this
        // EFFECTS: handles the removing of a book to the GUI and library
        public void actionPerformed(ActionEvent e) {

            int size = listModel.getSize();

            if (size == 0) {
                Toolkit.getDefaultToolkit().beep();
                return;

            } else {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                try {
                    library.removeBook(index);
                } catch (IndexException indexException) {
                    // index is not right
                }

                if (index == listModel.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);

                AudioPlayer.playSound(removeSound);
            }
        }
    }

    // represents the handler of the removeButton
    private class LoadListener implements ActionListener {
        private JButton button;

        // EFFECTS: constructs the button listener
        public LoadListener(JButton button) {
            this.button = button;
        }

        @Override
        // MODIFIES: LibraryGUI.this, this
        // EFFECTS: handles the loading of the library to the GUI
        public void actionPerformed(ActionEvent e) {
            library.getCatalogue().clear();
            listModel.clear();
            doLoadLibrary();
            updateListModel();
            button.setEnabled(false);
        }

        // MODIFIES: LibraryGUI.this
        // EFFECTS: loads library from file
        private void doLoadLibrary() {
            try {
                library = jsonReader.read();
            } catch (IOException e) {
                // loading went wrong
            }
        }

        // MODIFIES: LibraryGUI.this
        // EFFECTS: adds all the titles from library to the GUI list
        private void updateListModel() {
            for (Book b : library.getCatalogue()) {
                listModel.addElement(b.getTitle());
            }
        }
    }

    // represents the handler for saveButton
    private class SaveListener implements ActionListener {
        private JButton button;

        // EFFECTS: constructs the button listener
        public SaveListener(JButton button) {
            this.button = button;
        }

        @Override
        // EFFECTS: handles the saving of the GUI to the file
        public void actionPerformed(ActionEvent e) {
            doSaveLibrary();
        }

        // EFFECTS: saves library to file
        private void doSaveLibrary() {
            try {
                jsonWriter.open();
                jsonWriter.write(library);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                // file path wrong
            }
        }
    }

    // EFFECTS: runs and displays the LibraryGUI
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Library App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new LibraryGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    // starts LibraryGUI
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
