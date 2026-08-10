# Neo4j Cypher Scripts for 1Accounting

## Create Constraints and Indexes
```cypher
CREATE CONSTRAINT user_id IF NOT EXISTS
FOR (u:User) REQUIRE u.id IS UNIQUE;

CREATE CONSTRAINT organization_bin IF NOT EXISTS
FOR (o:Organization) REQUIRE o.bin IS UNIQUE;

CREATE CONSTRAINT account_code IF NOT EXISTS
FOR (a:ChartOfAccounts) REQUIRE a.code IS UNIQUE;

CREATE INDEX document_number_idx IF NOT EXISTS
FOR (d:Document) ON (d.documentNumber);

CREATE INDEX document_date_idx IF NOT EXISTS
FOR (d:Document) ON (d.documentDate);
```

## Sample Chart of Accounts (Kazakhstan)
```cypher
CREATE (a1010:ChartOfAccounts {id: 'acc-1010', code: '1010', nameRu: 'Денежные средства в кассе', nameKz: 'Кассадағы ақша қаражаттары', accountType: 'active', category: 'assets'})
CREATE (a1310:ChartOfAccounts {id: 'acc-1310', code: '1310', nameRu: 'Товары', nameKz: 'Тауарлар', accountType: 'active', category: 'assets'})
CREATE (a2410:ChartOfAccounts {id: 'acc-2410', code: '2410', nameRu: 'Краткосрочные кредиты банков', nameKz: 'Банктердің қысқа мерзімді несиелері', accountType: 'passive', category: 'liabilities'})
CREATE (a6010:ChartOfAccounts {id: 'acc-6010', code: '6010', nameRu: 'Доходы от реализации товаров', nameKz: 'Тауарларды сату түсімдері', accountType: 'passive', category: 'revenue'})
CREATE (a7010:ChartOfAccounts {id: 'acc-7010', code: '7010', nameRu: 'Себестоимость реализованных товаров', nameKz: 'Сатылған тауарлардың өзіндік құны', accountType: 'active', category: 'expenses'});
```

## Create Sample Organization
```cypher
CREATE (org:Organization {
  id: 'org-001',
  name: 'ТОО "Isayev Accounting"',
  bin: '940440001234',
  countryCode: 'KZ',
  address: 'г. Алматы, пр. Назарбаева, 1',
  phone: '+7 (727) 123-45-67',
  email: 'info@isayev.kz',
  taxRegistrationNumber: '940440001234',
  registrationDate: date('2024-01-15'),
  active: true,
  createdAt: datetime()
});
```
