package com.example.unbuttoned.Sketches;

/**
 * @author julian
 * Lambda/callback interface for successful challenge completion
 */
@FunctionalInterface
public interface IOnCompletion {
    void onCompletion(boolean successful);

}
