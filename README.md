## B2W star wars planet api

| Method | Path         | Body/Params                                                             | Description                                                           |
|--------|--------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------|
| POST   | /planets     | Body: { "name": "Endor", "terrain": "forests", "climate": "temperate" } | Route to create planet. Star wars api data is fetch async.            |
| GET    | /planets     | Param(Optional): name=Endor                                             | Without params return all planets. WIth params return planets by name |
| GET    | /planets/:id |                                                                         | Return Planet by id                                                   |
| DELETE | /planets/:id |                                                                         | Delete planet by id                                                   |