# Krav: Hårdkodad datastub för Team API

## Bakgrund

Implementera en provider för Team API (definierat i `rimfrost-service-team-openapi`) som returnerar
hårdkodad testdata. Syftet är att ha en fungerande stub att testa mot innan riktig integration
finns på plats.

## Testdata

### Individer

| Beteckning | typId                                  | varde       |
|------------|----------------------------------------|-------------|
| Individ A  | `116759e4-18fd-4209-849c-90abbd257d22` | `111111111` |
| Individ B  | `116759e4-18fd-4209-849c-90abbd257d22` | `222222222` |
| Individ C  | `116759e4-18fd-4209-849c-90abbd257d22` | `333333333` |

### Team

| teamId | namn           | kontor    | Medlemmar               |
|--------|----------------|-----------|-------------------------|
| 1      | Team Stockholm | Stockholm | inga                    |
| 2      | Team Göteborg  | Göteborg  | Individ A, Individ C    |
| 3      | Team Malmö     | Malmö     | Individ B               |
| 4      | Team Uppsala   | Uppsala   | Individ A och Individ B |

---

## Funktionella krav

### TEAM-FR-01 — Hämta teammedlemmar

- **TEAM-FR-01.1** `teamId=1` ska returnera en tom lista (`individer: []`).
- **TEAM-FR-01.2** `teamId=2` ska returnera Individ A och Individ C.
- **TEAM-FR-01.3** `teamId=3` ska returnera Individ B.
- **TEAM-FR-01.4** `teamId=4` ska returnera Individ A och Individ B.
- **TEAM-FR-01.5** Okänt `teamId` ska returnera HTTP 404.

### TEAM-FR-02 — Hämta individens team

- **TEAM-FR-02.1** Individ A (`varde=111111111`) ska returnera Team Göteborg (2) och Team Uppsala (4).
- **TEAM-FR-02.2** Individ B (`varde=222222222`) ska returnera Team Malmö (3) och Team Uppsala (4).
- **TEAM-FR-02.3** Individ C (`varde=333333333`) ska returnera Team Göteborg (2).
- **TEAM-FR-02.4** Okänt `idVarde` ska returnera HTTP 404.
- **TEAM-FR-02.5** Om `idTyp` inte matchar stub-datans `typId` ska HTTP 404 returneras.