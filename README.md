# 🛒 weekly-shopper-api 🛒

## Development

### With Docker

```bash
auto/dev-environment
```

### Running locally

```
./sbt run
```
App will be running on `http://localhost:8080/`

#### Running the database with Docker

```bash
docker-compose up db
```

#### Running the database locally

```bash
psql -h localhost -p 5432 -U postgres -d weekly_shopper_db
```


### Running the test suite

```
./sbt test
```

## Endpoints

#### GET /recipes
Gets ALL the recipes from the database.

```json
[
    {
        "id": 1,
        "category": "Breakfast",
        "name": "Choc Banana Protein Porridge",
        "ingredients": "3⁄4 cup (75g) rolled oats (or brown rice flakes or quinoa flakes if gluten free)\\n1 1⁄2 cups (375ml) water\\npinch of salt\\n1⁄2 cup (125ml) milk (or dairy-free alternative)\\n3 tbs (30g) natural protein powder\\n1 tbs cocoa powder plus extra for dusting\\n2 tsp maple syrup\\n1 tsp vanilla extract\\n1 medium banana half mashed & half sliced",
        "instructions": "Add oats, water and salt to a saucepan. Bring to a boil, then reduce to a gentle simmer.\\nAdd milk and stir constantly for 6-7 minutes or until oats have thickened and cooked through.\\nRemove from heat and stir through protein powder, cocoa, maple syrup, vanilla and mashed banana.\\nDivide between bowls and serve topped with sliced banana and a dusting of cocoa.",
        "duration": 15,
        "link": "https://centr.com/recipe/show/8471/choc-banana-protein-porridge",
        "imageLink": "https://cdn.centr.com/content/9000/8471/images/landscapewidedesktop1x-loup-cen-chocbananaprotienporridge-169.jpg",
        "createdAt": "2020-04-09T04:16:35.157579Z",
        "servings": 2
    },
    {
        "id": 2,
        "category": "Lunch",
        "name": "Tofu Tacos with Tahini",
        "ingredients": "240g firm tofu crumbled\\n1 tsp smoked paprika\\n1 tsp ground coriander\\nsalt & pepper, to taste\\n1⁄4 avocado\\n2 tsp lemon juice\\n4 small (25g) corn tortillas warmed\\n2 iceberg lettuce leaves shredded\\n40g red cabbage sliced\\n60g cherry tomatoes halved\\nDressing\\n4 tsp hulled tahini\\n6 tsp lemon juice\\n2 tbs (40ml) water\\n1⁄4 tsp ground chilli\\n1⁄4 tsp salt",
        "instructions": "In a bowl season tofu with paprika, coriander and salt and pepper.\\nHeat a non-stick frypan, cook tofu for 3-5 minutes.\\nPlace avocado in a small bowl, add lemon juice, season and mash to combine.\\nIn another bowl, combine all dressing ingredients together with a fork.\\nTo assemble tacos, spread avocado on each tortilla, top with lettuce, cabbage, tomatoes and tofu and drizzle over dressing to serve.",
        "duration": 15,
        "link": "https://centr.com/recipe/show/4661/tofu-tacos-with-tahini",
        "imageLink": "https://cdn.centr.com/content/5000/4661/images/landscapewidedesktop1x-loup-hco-tofutacoswithtahini-169l.jpg",
        "createdAt": "2020-04-09T04:16:44.389432Z",
        "servings": 2
    }
]
```

#### GET /recipes/by-category?{id}
Gets the recipes by a specified category.

```json
[
    {
        "id": 1,
        "category": "Breakfast",
        "name": "Choc Banana Protein Porridge",
        "ingredients": "3⁄4 cup (75g) rolled oats (or brown rice flakes or quinoa flakes if gluten free)\\n1 1⁄2 cups (375ml) water\\npinch of salt\\n1⁄2 cup (125ml) milk (or dairy-free alternative)\\n3 tbs (30g) natural protein powder\\n1 tbs cocoa powder plus extra for dusting\\n2 tsp maple syrup\\n1 tsp vanilla extract\\n1 medium banana half mashed & half sliced",
        "instructions": "Add oats, water and salt to a saucepan. Bring to a boil, then reduce to a gentle simmer.\\nAdd milk and stir constantly for 6-7 minutes or until oats have thickened and cooked through.\\nRemove from heat and stir through protein powder, cocoa, maple syrup, vanilla and mashed banana.\\nDivide between bowls and serve topped with sliced banana and a dusting of cocoa.",
        "duration": 15,
        "link": "https://centr.com/recipe/show/8471/choc-banana-protein-porridge",
        "imageLink": "https://cdn.centr.com/content/9000/8471/images/landscapewidedesktop1x-loup-cen-chocbananaprotienporridge-169.jpg",
        "createdAt": "2020-04-09T04:16:35.157579Z",
        "servings": 2
    }
]
```