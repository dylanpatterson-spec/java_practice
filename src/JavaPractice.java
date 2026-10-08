import arraysandbasics.CountAbovePreviousAverage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JavaPractice {
    private static final Color PAPER = new Color(244, 243, 237);
    private static final Color INK = new Color(32, 35, 31);
    private static final Color MUTED = new Color(112, 117, 106);
    private static final Color LINE = new Color(216, 217, 208);
    private static final Color GREEN_DARK = new Color(38, 55, 26);
    private static final Color CODE = new Color(32, 37, 31);
    private static final Font MONO = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    private static final Font DISPLAY = new Font(Font.SERIF, Font.PLAIN, 34);

    private final List<Challenge> challenges = createChallenges();
    private final JPanel challengeList = new JPanel();
    private final JLabel resultCount = new JLabel();
    private final JLabel emptyState = new JLabel("No challenges match that search.", SwingConstants.CENTER);
    private final JTextField search = new JTextField();
    private String activeTopic = "All";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException
                     | InstantiationException
                     | IllegalAccessException
                     | UnsupportedLookAndFeelException ignored) {
            }
            new JavaPractice().showWindow();
        });
    }

    private void showWindow() {
        JFrame frame = new JFrame("Java Practice | Solution Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(760, 620));
        frame.setSize(1000, 780);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(buildContent());
        frame.setVisible(true);
    }

    private JPanel buildContent() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(PAPER);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(PAPER);
        topBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, LINE),
                BorderFactory.createEmptyBorder(14, 34, 14, 34)));
        JLabel brand = new JLabel("■  JAVA PRACTICE");
        brand.setFont(MONO.deriveFont(Font.BOLD, 13f));
        brand.setForeground(INK);
        JLabel note = new JLabel("A FIELD GUIDE TO PROBLEM SOLVING");
        note.setFont(MONO.deriveFont(10f));
        note.setForeground(MUTED);
        topBar.add(brand, BorderLayout.WEST);
        topBar.add(note, BorderLayout.EAST);
        root.add(topBar, BorderLayout.NORTH);

        JPanel page = new JPanel(new BorderLayout(0, 22));
        page.setBackground(PAPER);
        page.setBorder(BorderFactory.createEmptyBorder(36, 54, 24, 54));
        page.add(buildIntro(), BorderLayout.NORTH);
        page.add(buildCatalog(), BorderLayout.CENTER);
        page.add(buildFooter(), BorderLayout.SOUTH);
        root.add(page, BorderLayout.CENTER);
        refreshChallenges();
        return root;
    }

    private JPanel buildIntro() {
        JPanel intro = new JPanel(new BorderLayout(24, 0));
        intro.setBackground(PAPER);
        JPanel text = new JPanel();
        text.setBackground(PAPER);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        JLabel kicker = new JLabel("●  SOLUTION LIBRARY / 001");
        kicker.setFont(MONO.deriveFont(Font.BOLD, 10f));
        kicker.setForeground(new Color(93, 105, 80));
        JLabel title = new JLabel("Java Practice");
        title.setFont(DISPLAY);
        title.setForeground(INK);
        JLabel description = new JLabel("A growing index of coding challenges, with each solution kept close to the idea it demonstrates.");
        description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        description.setForeground(new Color(94, 98, 90));
        text.add(kicker);
        text.add(Box.createVerticalStrut(10));
        text.add(title);
        text.add(Box.createVerticalStrut(8));
        text.add(description);

        JPanel stamp = new JPanel(new GridBagLayout());
        stamp.setBackground(PAPER);
        stamp.setBorder(BorderFactory.createLineBorder(INK));
        stamp.setPreferredSize(new Dimension(84, 84));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel number = new JLabel("04");
        number.setFont(DISPLAY.deriveFont(30f));
        number.setForeground(INK);
        stamp.add(number, constraints);
        constraints.gridy = 1;
        JLabel label = new JLabel("CHALLENGES");
        label.setFont(MONO.deriveFont(8f));
        label.setForeground(MUTED);
        stamp.add(label, constraints);
        intro.add(text, BorderLayout.CENTER);
        intro.add(stamp, BorderLayout.EAST);
        return intro;
    }

    private JPanel buildCatalog() {
        JPanel catalog = new JPanel(new BorderLayout(0, 10));
        catalog.setBackground(PAPER);
        catalog.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, INK));

        JPanel heading = new JPanel(new BorderLayout());
        heading.setBackground(PAPER);
        heading.setBorder(BorderFactory.createEmptyBorder(11, 0, 2, 0));
        JLabel title = new JLabel("Challenge index");
        title.setFont(DISPLAY.deriveFont(21f));
        title.setForeground(INK);
        resultCount.setFont(MONO.deriveFont(10f));
        resultCount.setForeground(MUTED);
        heading.add(title, BorderLayout.WEST);
        heading.add(resultCount, BorderLayout.EAST);

        JPanel controls = new JPanel(new BorderLayout(12, 8));
        controls.setBackground(PAPER);
        controls.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, LINE),
                BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        JPanel filters = new JPanel();
        filters.setBackground(PAPER);
        for (String topic : Arrays.asList("All", "Loops", "Strings", "Arrays", "Arrays and Basic Problem Solving")) {
            String label = topic.equals("Arrays and Basic Problem Solving") ? "Arrays + Basics" : topic;
            JButton filter = new JButton(label);
            filter.putClientProperty("topic", topic);
            filter.setFont(MONO.deriveFont(10f));
            filter.setFocusPainted(false);
            filter.setBorder(BorderFactory.createEmptyBorder(7, 11, 7, 11));
            filter.setOpaque(true);
            filter.addActionListener((ActionEvent event) -> {
                activeTopic = topic;
                for (java.awt.Component component : filters.getComponents()) {
                    JButton option = (JButton) component;
                    boolean selected = activeTopic.equals(option.getClientProperty("topic"));
                    option.setBackground(selected ? INK : PAPER);
                    option.setForeground(selected ? Color.WHITE : MUTED);
                }
                refreshChallenges();
            });
            boolean selected = topic.equals(activeTopic);
            filter.setBackground(selected ? INK : PAPER);
            filter.setForeground(selected ? Color.WHITE : MUTED);
            filters.add(filter);
        }
        search.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        search.setForeground(INK);
        search.setBackground(new Color(251, 250, 246));
        search.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), BorderFactory.createEmptyBorder(7, 10, 7, 10)));
        search.setPreferredSize(new Dimension(230, 34));
        search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) { refreshChallenges(); }
            @Override
            public void removeUpdate(DocumentEvent event) { refreshChallenges(); }
            @Override
            public void changedUpdate(DocumentEvent event) { refreshChallenges(); }
        });
        controls.add(filters, BorderLayout.WEST);
        controls.add(search, BorderLayout.EAST);

        challengeList.setBackground(PAPER);
        challengeList.setLayout(new BoxLayout(challengeList, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(challengeList);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(PAPER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        JPanel listing = new JPanel(new BorderLayout());
        listing.setBackground(PAPER);
        listing.add(scroll, BorderLayout.CENTER);
        emptyState.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        emptyState.setForeground(MUTED);
        listing.add(emptyState, BorderLayout.SOUTH);

        catalog.add(heading, BorderLayout.NORTH);
        JPanel body = new JPanel(new BorderLayout(0, 8));
        body.setBackground(PAPER);
        body.add(controls, BorderLayout.NORTH);
        body.add(listing, BorderLayout.CENTER);
        catalog.add(body, BorderLayout.CENTER);
        return catalog;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(PAPER);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, LINE));
        JLabel left = new JLabel("JAVA PRACTICE / SOLUTION LIBRARY");
        JLabel right = new JLabel("BUILT FOR CURIOSITY AND REPETITION");
        left.setFont(MONO.deriveFont(9f));
        right.setFont(MONO.deriveFont(9f));
        left.setForeground(MUTED);
        right.setForeground(MUTED);
        footer.add(left, BorderLayout.WEST);
        footer.add(right, BorderLayout.EAST);
        return footer;
    }

    private void refreshChallenges() {
        if (challengeList == null) return;
        String query = search.getText().trim().toLowerCase();
        List<Challenge> matches = new ArrayList<>();
        for (Challenge challenge : challenges) {
            boolean topicMatches = activeTopic.equals("All") || challenge.topic.equals(activeTopic);
            boolean queryMatches = query.isEmpty() || challenge.searchableText().contains(query);
            if (topicMatches && queryMatches) matches.add(challenge);
        }

        challengeList.removeAll();
        for (Challenge challenge : matches) {
            challengeList.add(buildChallengeRow(challenge));
        }
        challengeList.revalidate();
        challengeList.repaint();
        resultCount.setText(String.format("%02d %s", matches.size(), matches.size() == 1 ? "ENTRY" : "ENTRIES"));
        emptyState.setVisible(matches.isEmpty());
    }

    private JPanel buildChallengeRow(Challenge challenge) {
        JPanel row = new JPanel(new BorderLayout(18, 0));
        row.setBackground(PAPER);
        row.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LINE));
        row.setBorder(BorderFactory.createCompoundBorder(row.getBorder(), BorderFactory.createEmptyBorder(16, 0, 16, 0)));

        JLabel number = new JLabel(challenge.number);
        number.setFont(MONO.deriveFont(10f));
        number.setForeground(new Color(155, 158, 148));
        number.setVerticalAlignment(SwingConstants.TOP);

        JPanel content = new JPanel();
        content.setBackground(PAPER);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JPanel titleLine = new JPanel(new BorderLayout(12, 0));
        titleLine.setBackground(PAPER);
        JLabel title = new JLabel(challenge.title);
        title.setFont(DISPLAY.deriveFont(20f));
        title.setForeground(INK);
        JLabel topic = new JLabel(challenge.topic.toUpperCase());
        topic.setFont(MONO.deriveFont(9f));
        topic.setForeground(new Color(105, 123, 75));
        topic.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        JLabel difficulty = new JLabel("●  " + challenge.difficulty.toUpperCase());
        difficulty.setFont(MONO.deriveFont(9f));
        difficulty.setForeground(challenge.difficulty.equals("Medium") ? new Color(168, 122, 45) : MUTED);
        titleLine.add(title, BorderLayout.CENTER);
        titleLine.add(difficulty, BorderLayout.EAST);

        JLabel description = new JLabel("<html>" + challenge.description + "</html>");
        description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        description.setForeground(new Color(105, 109, 100));
        description.setBorder(BorderFactory.createEmptyBorder(6, 0, 10, 0));

        JPanel solutionContainer = new JPanel(new BorderLayout());
        solutionContainer.setBackground(PAPER);
        solutionContainer.setVisible(false);
        JButton toggle = new JButton("View Java solution  +");
        toggle.setFont(MONO.deriveFont(10f));
        toggle.setForeground(GREEN_DARK);
        toggle.setBackground(PAPER);
        toggle.setFocusPainted(false);
        toggle.setHorizontalAlignment(SwingConstants.LEFT);
        toggle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(157, 162, 143)),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)));
        toggle.addActionListener(event -> {
            boolean expanded = !solutionContainer.isVisible();
            solutionContainer.setVisible(expanded);
            toggle.setText(expanded ? "Hide Java solution  −" : "View Java solution  +");
            row.revalidate();
            row.repaint();
        });

        JPanel actions = new JPanel(new BorderLayout(8, 0));
        actions.setBackground(PAPER);
        actions.add(toggle, BorderLayout.WEST);
        if (challenge.consoleAction != null) {
            JButton run = new JButton("Run in console");
            run.setFont(MONO.deriveFont(10f));
            run.setForeground(GREEN_DARK);
            run.setBackground(new Color(198, 243, 107));
            run.setFocusPainted(false);
            run.addActionListener(event -> challenge.consoleAction.run());
            actions.add(run, BorderLayout.EAST);
        }

        JTextArea code = new JTextArea(challenge.solution);
        code.setEditable(false);
        code.setFont(MONO);
        code.setForeground(new Color(232, 236, 223));
        code.setBackground(CODE);
        code.setCaretPosition(0);
        code.setBorder(BorderFactory.createEmptyBorder(13, 14, 13, 14));
        code.setLineWrap(false);
        JScrollPane codeScroll = new JScrollPane(code);
        codeScroll.setBorder(BorderFactory.createEmptyBorder());
        codeScroll.setPreferredSize(new Dimension(600, 175));
        JLabel fileName = new JLabel(challenge.fileName + "   /   JAVA");
        fileName.setFont(MONO.deriveFont(9f));
        fileName.setForeground(new Color(179, 194, 152));
        fileName.setBorder(BorderFactory.createEmptyBorder(10, 14, 7, 14));
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.setBackground(CODE);
        codePanel.add(fileName, BorderLayout.NORTH);
        codePanel.add(codeScroll, BorderLayout.CENTER);
        JLabel output = new JLabel("<html><font color='#c6f36b'>" + challenge.example + "</font>  →  " + challenge.output + "</html>");
        output.setFont(MONO.deriveFont(10f));
        output.setForeground(new Color(213, 219, 203));
        output.setBorder(BorderFactory.createEmptyBorder(9, 0, 0, 0));
        solutionContainer.add(codePanel, BorderLayout.CENTER);
        solutionContainer.add(output, BorderLayout.SOUTH);

        content.add(topic);
        content.add(titleLine);
        content.add(description);
        content.add(actions);
        content.add(Box.createVerticalStrut(9));
        content.add(solutionContainer);
        row.add(number, BorderLayout.WEST);
        row.add(content, BorderLayout.CENTER);
        return row;
    }

    private static List<Challenge> createChallenges() {
        return Arrays.asList(
                new Challenge("01", "FizzBuzz", "Loops", "Easy",
                        "Replace multiples of three and five with their matching words.", "FizzBuzz.java",
                        """
                        static String[] fizzBuzz(int limit) {
                            String[] result = new String[limit];
                            for (int index = 1; index <= limit; index++) {
                                if (index % 15 == 0) result[index - 1] = "FizzBuzz";
                                else if (index % 3 == 0) result[index - 1] = "Fizz";
                                else if (index % 5 == 0) result[index - 1] = "Buzz";
                                else result[index - 1] = String.valueOf(index);
                            }
                            return result;
                        }
                        """, "fizzBuzz(5)", "[1, 2, Fizz, 4, Buzz]"),
                new Challenge("02", "Palindrome Check", "Strings", "Easy",
                        "Check whether text reads the same in both directions, ignoring punctuation and case.", "Palindrome.java",
                        """
                        static boolean isPalindrome(String text) {
                            int left = 0;
                            int right = text.length() - 1;
                            while (left < right) {
                                while (left < right && !Character.isLetterOrDigit(text.charAt(left))) left++;
                                while (left < right && !Character.isLetterOrDigit(text.charAt(right))) right--;
                                if (Character.toLowerCase(text.charAt(left)) != Character.toLowerCase(text.charAt(right))) return false;
                                left++;
                                right--;
                            }
                            return true;
                        }
                        """, "isPalindrome(\"A man, a plan, a canal: Panama\")", "true"),
                    new Challenge("03", "Count Elements Greater Than Previous Average", "Arrays and Basic Problem Solving", "Easy",
                        "Given an array of positive integers, return the number of elements strictly greater than the average of all previous elements. Skip the first element.",
                        "CountAbovePreviousAverage.java",
                        """
                        static int countElementsGreaterThanPreviousAverage(int[] values) {
                            int count = 0;
                            long previousSum = 0;
                            for (int index = 0; index < values.length; index++) {
                            if (index > 0 && (long) values[index] * index > previousSum) count++;
                            previousSum += values[index];
                            }
                            return count;
                        }
                        """, "countElementsGreaterThanPreviousAverage([10, 20, 30, 25, 40])", "4",
                        () -> {
                            int[] values = {10, 20, 30, 25, 40};
                            int result = CountAbovePreviousAverage.countElementsGreaterThanPreviousAverage(values);
                            System.out.printf("Count Elements Greater Than Previous Average%nInput: %s%nOutput: %d%n%n",
                                Arrays.toString(values), result);
                        }),
                    new Challenge("04", "Binary Search", "Arrays", "Medium",
                        "Find a target in a sorted array by halving the search range each step.", "BinarySearch.java", """
                                                                                                                       static int binarySearch(int[] values, int target) {
                                                                                                                           int low = 0;
                                                                                                                           int high = values.length - 1;
                                                                                                                           while (low <= high) {
                                                                                                                               int middle = low + (high - low) / 2;
                                                                                                                               if (values[middle] == target) return middle;
                                                                                                                               if (values[middle] < target) low = middle + 1;
                                                                                                                               else high = middle - 1;
                                                                                                                           }
                                                                                                                           return -1;
                                                                                                                       }""", "binarySearch([1, 3, 5, 7, 9], 7)", "3"));
    }

    private static final class Challenge {
        private final String number;
        private final String title;
        private final String topic;
        private final String difficulty;
        private final String description;
        private final String fileName;
        private final String solution;
        private final String example;
        private final String output;
        private final Runnable consoleAction;

        private Challenge(String number, String title, String topic, String difficulty, String description,
                          String fileName, String solution, String example, String output) {
            this(number, title, topic, difficulty, description, fileName, solution, example, output, null);
        }

        private Challenge(String number, String title, String topic, String difficulty, String description,
                          String fileName, String solution, String example, String output, Runnable consoleAction) {
            this.number = number;
            this.title = title;
            this.topic = topic;
            this.difficulty = difficulty;
            this.description = description;
            this.fileName = fileName;
            this.solution = solution;
            this.example = example;
            this.output = output;
            this.consoleAction = consoleAction;
        }

        private String searchableText() {
            return (number + " " + title + " " + topic + " " + difficulty + " " + description + " " +
                    fileName + " " + solution + " " + example + " " + output).toLowerCase();
        }
    }
}