package com.company

// Groovy 4 level lock: native records are a Groovy 4.0 feature - this fixture fails to
// compile if the build-time Groovy silently reverts to a 3.x level
record MyRecord(String name, int value) {
}
