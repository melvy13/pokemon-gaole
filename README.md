# prg1203-pokessignment
Java console program that imitates the Pokemon Ga-Ole game

UML Diagram: https://app.diagrams.net/#G1NdVyJooq2UGCAlhIzcAXmQYXqX1VvJNd

Program Flow Draft: https://docs.google.com/document/d/1wL7e-JiF5f8D7hEwn5blS1R62kNSNY3vZR4nuj5tty8/edit

Pokemon Damage Calculation
Assume:
Grade1 Pokemon Level = 30
Grade2 Pokemon Level = 40
Grade3 Pokemon Level = 50
Grade4 Pokemon Level = 60
All AttackPower = 50
No STAB effect
RandomNumber between 85-100

Damage = ((0.4 * level + 2) * Attack / Defense + 2) * Effectiveness * Random.nextDouble(0.85, 1.00)
