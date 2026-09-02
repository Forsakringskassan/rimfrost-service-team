# rimfrost-service-team changelog

Changelog of rimfrost-service-team.

## 0.1.0 (2026-09-01)

### Features

-  adds published openapi endpoint to repo ([79e02](https://github.com/Forsakringskassan/rimfrost-service-team/commit/79e0260dccf584c) LisaWedin_Ductus)  
-  Add Behorighet model and hardcoded test data ([44cc8](https://github.com/Forsakringskassan/rimfrost-service-team/commit/44cc86c2e8992a6) LisaWedin_Ductus)  

### Bug Fixes

-  change to return false instead of null if handlaggare not found ([cce22](https://github.com/Forsakringskassan/rimfrost-service-team/commit/cce22d1dbceb3e3) Nils Elveros)  
-  update the service with the endpount hasSidPermission ([0468a](https://github.com/Forsakringskassan/rimfrost-service-team/commit/0468ae7ea13cfc4) Nils Elveros)  
-  Remove duplicate Behorighet enum, use generated model directly ([19e8f](https://github.com/Forsakringskassan/rimfrost-service-team/commit/19e8f82c39a23f6) LisaWedin_Ductus)  
-  Handle null idTyp/idVarde in BehorighetStub and deduplicate identity check ([513aa](https://github.com/Forsakringskassan/rimfrost-service-team/commit/513aa21c496901e) LisaWedin_Ductus)  

## 0.0.4 (2026-08-19)

### Bug Fixes

-  bump team-openapi-jaxrs-spec to 0.0.2 with corrected package name ([9b5cf](https://github.com/Forsakringskassan/rimfrost-service-team/commit/9b5cfcccc0ee916) Ulf Slunga)  

## 0.0.3 (2026-08-18)

### Bug Fixes

-  Add quarkus-smallrye-health dependency for health endpoint ([acd23](https://github.com/Forsakringskassan/rimfrost-service-team/commit/acd23019de55302) Ulf Slunga)  

## 0.0.2 (2026-08-18)

### Bug Fixes

-  Parse idTyp as UUID before comparing to handle case-insensitive input ([ec661](https://github.com/Forsakringskassan/rimfrost-service-team/commit/ec6619f7d1f7b05) Ulf Slunga)  
-  Validate idTyp in getIndividTeam and align TYP_ID to HANDLAGGARE_ID ([b4cb3](https://github.com/Forsakringskassan/rimfrost-service-team/commit/b4cb393216d3b3d) Ulf Slunga)  

## 0.0.1 (2026-08-18)

### Bug Fixes

-  Implement hardcoded team data stub with tests ([8221b](https://github.com/Forsakringskassan/rimfrost-service-team/commit/8221ba15f56d1b2) Ulf Slunga)  
-  Add implementation plan for hardcoded team data stub ([2cae4](https://github.com/Forsakringskassan/rimfrost-service-team/commit/2cae4eb933515df) Ulf Slunga)  
-  Add requirements and populate README ([0fe3b](https://github.com/Forsakringskassan/rimfrost-service-team/commit/0fe3bccc4915edd) Ulf Slunga)  

### Other changes

**Add Maven wrapper scripts and GitHub server settings**


[a34c6](https://github.com/Forsakringskassan/rimfrost-service-team/commit/a34c6d3cd411613) Ulf Slunga *2026-08-14 07:23:14*

**Create maven-release.yaml**


[bd47c](https://github.com/Forsakringskassan/rimfrost-service-team/commit/bd47ce635db74f6) Ulf Slunga *2026-08-14 07:19:02*

**Create maven-ci.yaml**


[6a39a](https://github.com/Forsakringskassan/rimfrost-service-team/commit/6a39a9bdef061c2) Ulf Slunga *2026-08-14 07:18:24*

**Initial commit**


[9dc97](https://github.com/Forsakringskassan/rimfrost-service-team/commit/9dc973a1baf808b) Ulf Slunga *2026-08-14 06:57:36*


