<!-- Generator: Widdershins v4.0.1 -->

<h1 id="customer-contacts-api">Customer contacts API v1.0.0</h1>

> Two steps to start application
> 1. download or clone code
> 2. run `./gradlew clean bootRun` on linux or `gradlew.bat clean bootRun` on winwows

Simple API allows to create and view customers and contacts

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

## createCustomer

<a id="opIdcreateCustomer"></a>

> Code samples

```http
POST http://localhost:8080/api/v1/customers HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/v1/customers \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json'

```

`POST /api/v1/customers`

*POST api/v1/customers*

> Body parameter

```json
{
  "name": "test1",
}
```

<h3 id="createcustomer-parameters">Parameters</h3>

|Name|In|Type|Required| Description              |
|---|---|---|---|--------------------------|
|body|body|[CustomerDto](#schemacustomerdto)|true| only `name` field needed |

> Example responses

> 200 Response

```json
{
  "id": 1,
  "name": "test1",
  "contactsCount": 2
}
```

<h3 id="createcustomer-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[CustomerDto](#schemacustomerdto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Invalid customer name supplied|[ErrorResponse](#schemaerrorresponse)|
|422|[Unprocessable Entity](https://tools.ietf.org/html/rfc2518#section-10.3)|Customer saving failed because of DB constraints violation|[ErrorResponse](#schemaerrorresponse)|

<aside class="success">
This operation does not require authentication
</aside>

## getCustomers

<a id="opIdgetCustomers"></a>

> Code samples

```http
GET http://localhost:8080/api/v1/customers HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/v1/customers \
  -H 'Accept: application/json'

```

`GET /api/v1/customers`

*GET api/v1/customers*

<h3 id="getcustomers-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|pageNumber|query|integer(int32)|false|number of page, 0 if omitted or less than zero|
|pageSize|query|integer(int32)|false|page size, 2147483647 if omitted or less than 1|

> Example responses

> 200 Response

```json
{
  "content": [
    {
      "id": 10,
      "name": "test test"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 2147483647,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 4,
  "size": 2147483647,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 4,
  "empty": false
}
```

<h3 id="getcustomers-responses">Responses</h3>

|Status|Meaning|Description| Schema                                                     |
|---|---|---|------------------------------------------------------------|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK| [Page&lt;CustomerBriefDto>](#schemapage<customerbriefdto>) |
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Invalid pageNumber or pageSize (letters for example) parameters supplied| [ErrorResponse](#schemaerrorresponse)                      |

<aside class="success">
This operation does not require authentication
</aside>

## addContact

<a id="opIdaddContact"></a>

> Code samples

```http
POST http://localhost:8080/api/v1/customers/{customerId}/contacts HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/v1/customers/{customerId}/contacts \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json'

```

`POST /api/v1/customers/{customerId}/contacts`

*POST api/v1/customers/{customerId}/contacts*

> Body parameter

```json
{
  "contactType": "PHONE",
  "contactValue": "+7(777)777-77-77"
}
```

<h3 id="addcontact-parameters">Parameters</h3>

|Name|In|Type|Required| Description                                           |
|---|---|---|---|-------------------------------------------------------|
|customerId|path|integer(int64)|true| none                                                  |
|body|body|[ContactDto](#schemacontactdto)|true| `contactType` and  `contactValue` fields are required |

> Example responses

> 200 Response

```json
{
  "id": 5,
  "contactType": "PHONE",
  "contactValue": "+7(777)777-77-77"
}
```

<h3 id="addcontact-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ContactDto](#schemacontactdto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Invalid customer ID or/and invalid contact parameters supplied|[ErrorResponse](#schemaerrorresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Customer not found|[ErrorResponse](#schemaerrorresponse)|
|422|[Unprocessable Entity](https://tools.ietf.org/html/rfc2518#section-10.3)|Contact saving failed because of DB constraints violation|[ErrorResponse](#schemaerrorresponse)|

<aside class="success">
This operation does not require authentication
</aside>

## getCustomerContacts

<a id="opIdgetCustomerContacts"></a>

> Code samples

```http
GET http://localhost:8080/api/v1/customers/{customerId}/contacts HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/v1/customers/{customerId}/contacts \
  -H 'Accept: application/json'

```

`GET /api/v1/customers/{customerId}/contacts`

*GET api/v1/customers/{customerId}/contacts*

<h3 id="getcustomercontacts-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|customerId|path|integer(int64)|true|none|
|type|query|string|false|none|

#### Enumerated Values

|Parameter|Value|
|---|---|
|type|EMAIL|
|type|PHONE|

> Example responses

> 200 Response

```json
[
  {
    "id": 5,
    "contactType": "PHONE",
    "contactValue": "+7(777)777-77-77"
  }
]
```

<h3 id="getcustomercontacts-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Invalid customer ID supplied|[ErrorResponse](#schemaerrorresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Customer not found|[ErrorResponse](#schemaerrorresponse)|

<h3 id="getcustomercontacts-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[ContactDto](#schemacontactdto)]|false|none|none|
|» id|integer(int64)|false|none|internal contact ID, starts with 1|
|» contactType|string|true|none|contact type|
|» contactValue|string|true|none|contact value - email address or phone number|

#### Enumerated Values

|Property|Value|
|---|---|
|contactType|EMAIL|
|contactType|PHONE|

<aside class="success">
This operation does not require authentication
</aside>

## getCustomer

<a id="opIdgetCustomer"></a>

> Code samples

```http
GET http://localhost:8080/api/v1/customers/{customerId} HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/v1/customers/{customerId} \
  -H 'Accept: application/json'

```

`GET /api/v1/customers/{customerId}`

*GET api/v1/customers/{customerId}*

<h3 id="getcustomer-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|customerId|path|integer(int64)|true|none|

> Example responses

> 200 Response

```json
{
  "id": 1,
  "name": "test1",
  "contactsCount": 2
}
```

<h3 id="getcustomer-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[CustomerDto](#schemacustomerdto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Invalid customer ID supplied|[ErrorResponse](#schemaerrorresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Customer not found|[ErrorResponse](#schemaerrorresponse)|

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_Page<CustomerBriefDto>">Page&lt;CustomerBriefDto></h2>
<!-- backwards compatibility -->
<a id="schemapage<customerbriefdto>"></a>
<a id="schema_Page<CustomerBriefDto>"></a>
<a id="tocSpage<customerbriefdto>"></a>
<a id="tocspage<customerbriefdto>"></a>

```json
{
  "content": [
    {
      "id": 10,
      "name": "test test"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 2147483647,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 4,
  "size": 2147483647,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 4,
  "empty": false
}

```

page of customers sorted by ID ascending

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|content|[[CustomerBriefDto](#schemacustomerbriefdto)]|false|none|page of customers in brief format with page size specified in pageable.pageSize|
|pageable|object|false|none|none|
|» pageNumber|integer(int32)|false|none|page number|
|» pageSize|integer(int32)|false|none|maximum elements count for page|
|» sort|[Sort](#schemasort)|false|none|sort flags|
|» offset|integer(int64)|false|none|none|
|» paged|boolean|false|none|none|
|» unpaged|boolean|false|none|none|
|last|boolean|false|none|true if current page is last|
|totalPages|integer(int32)|false|none|overall page count|
|totalElements|integer(int64)|false|none|total customers count|
|size|integer(int32)|false|none|page size|
|number|integer|false|none|page number|
|sort|[Sort](#schemasort)|false|none|sort flags|
|first|boolean|false|none|true if current page is first|
|numberOfElements|integer(int32)|false|none|count of elements placed on current page|
|empty|boolean|false|none|true if page is empty|

<h2 id="tocS_Sort">Sort</h2>
<!-- backwards compatibility -->
<a id="schemasort"></a>
<a id="schema_Sort"></a>
<a id="tocSsort"></a>
<a id="tocssort"></a>

```json
{
  "empty": false,
  "sorted": true,
  "unsorted": false
}

```

sort flags

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|empty|boolean|false|none|none|
|sorted|boolean|false|none|none|
|unsorted|boolean|false|none|none|

<h2 id="tocS_CustomerDto">CustomerDto</h2>
<!-- backwards compatibility -->
<a id="schemacustomerdto"></a>
<a id="schema_CustomerDto"></a>
<a id="tocScustomerdto"></a>
<a id="tocscustomerdto"></a>

```json
{
  "id": 1,
  "name": "test1",
  "contactsCount": 2
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|internal customer ID, starts with 1|
|name|string|true|none|customer name|
|contactsCount|integer(int64)|false|none|count of customer contacts|

<h2 id="tocS_ContactDto">ContactDto</h2>
<!-- backwards compatibility -->
<a id="schemacontactdto"></a>
<a id="schema_ContactDto"></a>
<a id="tocScontactdto"></a>
<a id="tocscontactdto"></a>

```json
{
  "id": 5,
  "contactType": "PHONE",
  "contactValue": "+7(777)777-77-77"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|internal contact ID, starts with 1|
|contactType|string|true|none|contact type|
|contactValue|string|true|none|contact value - email address or phone number|

#### Enumerated Values

|Property|Value|
|---|---|
|contactType|EMAIL|
|contactType|PHONE|

<h2 id="tocS_CustomerBriefDto">CustomerBriefDto</h2>
<!-- backwards compatibility -->
<a id="schemacustomerbriefdto"></a>
<a id="schema_CustomerBriefDto"></a>
<a id="tocScustomerbriefdto"></a>
<a id="tocscustomerbriefdto"></a>

```json
{
  "id": 10,
  "name": "test test"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|internal customer ID|
|name|string|false|none|customer name|

<h2 id="tocS_ErrorResponse">ErrorResponse</h2>
<!-- backwards compatibility -->
<a id="schemaerrorresponse"></a>
<a id="schema_ErrorResponse"></a>
<a id="tocSerrorresponse"></a>
<a id="tocserrorresponse"></a>

```json
{
  "type": "about:blank",
  "title": "Unprocessable Entity",
  "status": "422,",
  "detail": "can't save or update entity",
  "instance": "/api/v1/customers",
  "timestamp": "2023-09-13T11:57:37.6733499+03:00"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|type|string|false|none|none|
|title|string|false|none|none|
|status|integer(http status code)|false|none|none|
|detail|string|false|none|none|
|instance|string(resource path)|false|none|none|
|timestamp|string(timestamp with time-zone)|false|none|none|

