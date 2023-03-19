package app.ui.terminal.output.element;

import app.ui.terminal.output.OutStream;

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

    public SimpleProgressBar(OutStream outStream, int consoleWidth, double currentResult, double resultComparingTo) {
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
        printStandardBar(numberOfFullTiles, numberOfSpaces);
    }

    private void printSurpassingBar() {
        int surpassingPartLocation = (int) (consoleWidth / dayRatio) + 1;
        int numberOfTilesInsideBar = surpassingPartLocation - closingAndStartingSeqsLen;
        int tilesOutOfBar = consoleWidth - surpassingPartLocation;
        printSurpassingBar(numberOfTilesInsideBar, tilesOutOfBar);
    }

    private void printStandardBar(int numberOfFullTiles, int numberOfSpaces) {
        outStream.print(STARTING_SEQ);
        outStream.print(generateCharSeq(FULL_TILE, numberOfFullTiles));
        outStream.print(generateCharSeq(SPACE, numberOfSpaces));
        outStream.println(CLOSING_SEQ);
    }

    private void printSurpassingBar(int numberOfTilesInsideBar, int tilesOutOfBar) {
        outStream.print(STARTING_SEQ);
        outStream.print(generateCharSeq(FULL_TILE, numberOfTilesInsideBar));
        outStream.print(CLOSING_SEQ);
        outStream.println(generateCharSeq(FULL_TILE, tilesOutOfBar));
    }

    private String generateCharSeq(char c, int n) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sBuilder.append(c);
        }
        return sBuilder.toString();
    }
}
