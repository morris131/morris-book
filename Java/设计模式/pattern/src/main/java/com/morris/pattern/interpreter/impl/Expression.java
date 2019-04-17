package com.morris.pattern.interpreter.impl;

import java.util.HashMap;

public interface Expression {

    int interpret(HashMap<String, Integer> ctx);
}
