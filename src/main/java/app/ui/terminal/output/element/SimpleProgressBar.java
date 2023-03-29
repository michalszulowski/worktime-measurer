package app.ui.terminal.output.element;

import app.ui.terminal.output.OutStream;
import app.util.TextUtils;

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
        outStream.print(TextUtils.generateCharSeq(FULL_TILE, numberOfFullTiles));
        outStream.print(TextUtils.generateCharSeq(SPACE, numberOfSpaces));
        outStream.println(CLOSING_SEQ);
    }

    private void printSurpassingBar(int numberOfTilesInsideBar, int tilesOutOfBar) {
        outStream.print(STARTING_SEQ);
        outStream.print(TextUtils.generateCharSeq(FULL_TILE, numberOfTilesInsideBar));
        outStream.print(CLOSING_SEQ);
        outStream.println(TextUtils.generateCharSeq(FULL_TILE, tilesOutOfBar));
    }
}
