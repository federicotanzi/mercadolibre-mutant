package com.mercadolibre.mutante.detector;

public class DiagonalDownProcessor extends MutantProcessor {

    public DiagonalDownProcessor(MutantContext context) {
        super(context);
    }

    @Override
    public void searchSequences() {
        boolean match = findMutantSequence(new PositionContext(0, 0));
        if (match) {
            return;
        }
        for (int row = 1; row <= context.getDnaLength() - context.getSequenceSizeMutant(); row++) {
            match = findMutantSequence(new PositionContext(row, 0, row)) || findMutantSequence(new PositionContext(0, row, row));
            if (match) {
                break;
            }
        }
    }

    @Override
    protected void next(PositionContext positionContext) {
        positionContext.addRow();
        positionContext.addColumn();
        positionContext.addSubIndex();
        positionContext.setLastChar(positionContext.getCurrentChar());
        positionContext.setCurrentChar(this.getCurrentChar(positionContext.getRow(),positionContext.getColumn()));
    }

    @Override
    protected boolean hasNext(PositionContext positionContext, int actualSequence) {
        return positionContext.getSize() - positionContext.getSubIndex() + actualSequence >= context.getSequenceSizeMutant()
                && Math.max(positionContext.getColumn(), positionContext.getRow()) + 1 < positionContext.getSize();
    }

}
