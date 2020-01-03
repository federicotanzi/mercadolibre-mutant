package com.mercadolibre.mutante.detector;

public class HorizontalProcessor extends MutantProcessor {

    public HorizontalProcessor(MutantContext context) {
        super(context);
    }

    @Override
    public void searchSequences() {
        for (int row = 0; row < context.getDnaLength(); row++) {
            boolean match = findMutantSequence(new PositionContext(row, 0 , context));
            if (match) {
                break;
            }
        }
    }

    @Override
    protected void next(PositionContext positionContext) {
        positionContext.addColumn();
        positionContext.addSubIndex();
        positionContext.setLastChar(positionContext.getCurrentChar());
        positionContext.setCurrentChar(this.getCurrentChar(positionContext.getRow(),positionContext.getColumn()));
    }

    @Override
    protected boolean hasNext(PositionContext positionContext, int actualSequence) {
        return positionContext.getColumn() + 1 <= positionContext.getSafeIndex();
    }

}
