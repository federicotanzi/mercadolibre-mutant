package com.mercadolibre.mutante.detector;

public class DiagonalUpProcessor extends MutantProcessor {

    public DiagonalUpProcessor(MutantContext context) {
        super(context);
    }

    @Override
    public void searchSequences() {
        int fistIndexRow = context.getDnaLength() - 1;
        int fistIndexColumn = 0;
        boolean match = findMutantSequence(new PositionContext(fistIndexRow, fistIndexColumn));
        if (match) {
            return;
        }
        for (int row = 1; row <= context.getDnaLength() - context.getSequenceSizeMutant(); row++) {
            match = findMutantSequence(new PositionContext(fistIndexRow - row, 0, row))  || findMutantSequence(new PositionContext(fistIndexRow, row, row));
            if (match) {
                break;
            }
        }
    }

    @Override
    protected void next(PositionContext positionContext) {
        positionContext.subtractRow();
        positionContext.addColumn();
        positionContext.addSubIndex();
        positionContext.setLastChar(positionContext.getCurrentChar());
        positionContext.setCurrentChar(this.getCurrentChar(positionContext.getRow(),positionContext.getColumn()));
    }

    @Override
    protected boolean hasNext(PositionContext positionContext, int actualSequence) {
        return positionContext.getSize() - positionContext.getSubIndex() + actualSequence >= context.getSequenceSizeMutant() && positionContext.getRow() - 1 >= 0;
    }

}
