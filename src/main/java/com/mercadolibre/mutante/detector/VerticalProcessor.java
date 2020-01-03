package com.mercadolibre.mutante.detector;

public class VerticalProcessor extends MutantProcessor {

    public VerticalProcessor(MutantContext context) {
        super(context);
    }

    @Override
    public void searchSequences() {
        for (int column = 0; column < context.getDnaLength(); column++) {
            boolean match = findMutantSequence(new PositionContext(0, column));
            if (match) {
                break;
            }
        }
    }

    @Override
    protected void next(PositionContext positionContext) {
        positionContext.addRow();
        positionContext.addSubIndex();
        positionContext.setLastChar(positionContext.getCurrentChar());
        positionContext.setCurrentChar(this.getCurrentChar(positionContext.getRow(),positionContext.getColumn()));
    }

    @Override
    protected boolean hasNext(PositionContext positionContext, int actualSequence) {
        return positionContext.getRow() + 1 <= positionContext.getSafeIndex();
    }

}
