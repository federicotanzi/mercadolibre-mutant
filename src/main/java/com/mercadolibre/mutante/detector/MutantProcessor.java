package com.mercadolibre.mutante.detector;

public abstract class MutantProcessor {

    protected MutantContext context;

    public MutantProcessor(MutantContext context) {
        this.context = context;
    }

    public abstract void searchSequences();

    protected abstract void next(PositionContext positionContext);

    protected abstract boolean hasNext(PositionContext positionContext, int actualSequence);

    protected char getCurrentChar(int row, int column){
        return context.getDnaChar(row, column);
    }

    protected boolean findMutantSequence(PositionContext positionContext) {
        char currentChar = this.getCurrentChar(positionContext.getRow(),positionContext.getColumn());
        int sequence = 1;
        while (hasNext(positionContext, sequence)) {
            next(positionContext);

            if (currentChar != positionContext.getCurrentChar()) {
                sequence = 1;
                currentChar = positionContext.getCurrentChar();
            } else if (++sequence >= context.getSequenceSizeMutant()) {
                context.addQuantityMutantSequences();

                if (context.hasMatchQuantitySequencesMutant()) {
                    return true;
                }
            }
        }
        return false;
    }
}
