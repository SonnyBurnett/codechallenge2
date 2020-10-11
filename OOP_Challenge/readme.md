# OOP Challenge

Write a program that:​
- Reads all inputs.csv​
- Writes a file outputs.csv that:​
    - Contains the same products as from products.csv​
    - Leaving out all products with a price below $ 10​
    - Writes out prices in euro’s, assuming a rate of $ 1 : € 0,85​

Input file:
```csv
productId,	name,		description,	price,	category​
45848,		shorts,		short pants,	8,	pants​
4184688,	trousers,	trousers,	12,	pants​
848488,		blue shirt,	shirt,		88,	shirts
```

Output file:​
```csv
productId,	name,		description,	price,	category​
4184688,	trousers,	trousers,		10.2,	pants​
848488,		blue shirt,	shirt,			74.8,	shirts
```

scoring:

TODO: add this section