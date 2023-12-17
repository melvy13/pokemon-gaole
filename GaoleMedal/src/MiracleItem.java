
abstract class MiracleItem { // abstract class - cannot be used to create objects (can only access through subclass)
	abstract String use();
}

class AttackCapsule extends MiracleItem {
    @Override
    String use() {
        return "Attack power boosted!";
    }
}

class DefenseCapsule extends MiracleItem {
    @Override
    String use() {
        return "Defense power boosted!";
    }
}

class PokeBallPower extends MiracleItem {
    @Override
    String use() {
        return "PokeBall upgraded for after the battle!";
    }
}


