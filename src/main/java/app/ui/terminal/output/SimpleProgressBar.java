package app.ui.terminal.output;

public class SimpleProgressBar implements TerminalFrameElement {
    private OutStream outStream;
    private int consoleWidth;
    private final String STARTING_SEQ = "[";
    private final String CLOSING_SEQ = "]";
    private final char FULL_TILE = '█';
    private final char SPACE = ' ';
    private int closingAndStartingSeqsLen;
    private double currentResult;
    private double resultComparingTo;
    private double dayRatio;

    public SimpleProgressBar(OutStream outStream, int consoleWidth, float currentResult, float resultComparingTo) {
        this.outStream = outStream;
        this.consoleWidth = consoleWidth;
        this.currentResult = currentResult;
        this.resultComparingTo = resultComparingTo;
    }

    @Override
    public void print() {
        calculateData();
        if (currentResult <= resultComparingTo) {
            printStandardBar();
        } else {
            printSurpassingBar();
        }
    }

    private void calculateData() {
        closingAndStartingSeqsLen = STARTING_SEQ.length() + CLOSING_SEQ.length();
        dayRatio = currentResult / resultComparingTo;
    }

    private void printStandardBar() {
        int progressBarWidth = consoleWidth;
        int onlyBarWidth = progressBarWidth - closingAndStartingSeqsLen;
        int numberOfFullTiles = Math.max((int) (onlyBarWidth * dayRatio), 0);
        int numberOfSpaces = onlyBarWidth - numberOfFullTiles;
        outStream.print(STARTING_SEQ);
        outStream.print(generateCharSeq(FULL_TILE, numberOfFullTiles));
        outStream.print(generateCharSeq(SPACE, numberOfSpaces));
        outStream.println(CLOSING_SEQ);
    }

    private void printSurpassingBar() {
        int surpassingPartLocation = (int) (consoleWidth / dayRatio) + 1;
        outStream.print(STARTING_SEQ);
        int numberOfTilesInsideBar = surpassingPartLocation - closingAndStartingSeqsLen;
        outStream.print(generateCharSeq(FULL_TILE, numberOfTilesInsideBar));
        outStream.print(CLOSING_SEQ);
        int tilesOutOfBar = consoleWidth - surpassingPartLocation;
        outStream.println(generateCharSeq(FULL_TILE, tilesOutOfBar));
    }

    private String generateCharSeq(char c, int n) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sBuilder.append(c);
        }
        return sBuilder.toString();
    }

    public static void main(String[] args) {
        getBarOf(5.5f, 4.f).print();
        getBarOf(4.f, 5.5f).print();
        getBarOf(0f, 4.f).print();
        getBarOf(4.f, 4.f).print();
        getBarOf(4.01f, 4.f).print();
        getBarOf(2.f, 3.f).print();

    }

    private static SimpleProgressBar getBarOf(float hoursWorked, float hoursWorkedDayBefore) {
        int consoleWidth = 20;
        return new SimpleProgressBar(new TerminalOutStream(), consoleWidth, hoursWorked, hoursWorkedDayBefore);
    }
}