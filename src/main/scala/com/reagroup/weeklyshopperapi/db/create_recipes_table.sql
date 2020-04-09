CREATE TABLE recipes(
    id INT PRIMARY KEY,
    type INT NOT NULL,
    name VARCHAR NOT NULL,
    ingredients VARCHAR NOT NULL,
    instruction VARCHAR NOT NULL,
    duration INT NOT NULL,
    link VARCHAR,
    image_link VARCHAR,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    servings INT NOT NULL
);