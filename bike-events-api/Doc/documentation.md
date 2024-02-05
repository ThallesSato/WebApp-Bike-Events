
# Bike events API Doc
**Contact information:**  
**Name**: Thalles Sato  
https://www.linkedin.com/in/thalles-sato-79381b192/  
thalles_sato@hotmail.com  
Phone: +55 (11) 95647-6237 
**License:** MIT License

## Open Endpoints

Open endpoints require no Authentication.

### Authentication

<details>
 <summary><code>POST /auth/register</code> Register </summary>
 
##### Request body

 *Scheme: AuthenticationModel*
>  Field  | Type |  Mandatory
>  ------ | --------- | ----
> username| String | yes
> password | String | yes

##### Responses

>  Code | Scheme | Message
>  ------ | --------- |----
> 200 | Token |None
> 400 | ErrorResponse | `Username already used`

</details>

<details>
 <summary><code>POST /auth/login</code> Login</summary>
 
##### Request body

 *Scheme: AuthenticationModel*
>  Field  | Type | Mandatory
>  ------ | --------- | -----
> username| String | yes
> password | String | yes
 
##### Responses

>  Code | Scheme | Message
>  ------ | --------- |----
> 200 | Token |None
> 401 | ErrorResponse | `Invalid login`

</details>

## Closed Endpoints

Closed endpoints require a valid bearer token in header. Token can be acquired from Register or Login endpoints.

### Ride
  
<details>
 <summary><code>GET /ride</code> List rides </summary>
 
##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Ride
</details>
<details>
 <summary><code>GET /ride/{id}</code> Get ride by id </summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)

##### Responses

>  Code | Scheme 
>  ------ | --------- 
>    200 | Ride 
>    404 | ErrorResponse 
</details>
<details>
 <summary><code>POST /ride</code> Create ride </summary>
 
##### Request body

 *Scheme: RideDto*
>  Field  | Type | Mandatory
>  ------ | --------- | ------
> name | String | yes
> start_date| String($date-time) | yes
> start_date_registration| String($date-time) | yes
> end_date_registration| String($date-time) | yes
> additional_information| String  | no
> start_place| String  | yes
> participants_limit| Integer($int32) | no for unlimited
> client_id| Integer($int32) | yes

##### Responses

>  Code | Scheme
>  ------ | --------- 
>    201 | Ride 
>    400 | ErrorResponse 
</details>
<details>
 <summary><code>PUT /ride/{id}</code> Modify ride </summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)
 
##### Request body

 *Scheme: RideDto*
>  Field  | Type | Mandatory
>  ------ | --------- | ------
> name | String | yes
> start_date| String($date-time) | yes
> start_date_registration| String($date-time) | yes
> end_date_registration| String($date-time) | yes
> additional_information| String  | no
> start_place| String  | yes
> participants_limit| Integer($int32) | no for unlimited
> client_id| Integer($int32) | yes

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Ride 
>    400 | ErrorResponse 
>    404 | ErrorResponse 
</details>
<details>
 <summary><code>DELETE /ride/{id}</code> Delete ride </summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)

##### Responses

>  Code | Scheme | Message
>  ------ | --------- | ---
>    200 | None | `Client deleted successful`
>    404 | ErrorResponse | 
</details>

### Client

<details>
 <summary><code>GET /client</code> List clients</summary>

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Client
</details>

<details>
 <summary><code>GET /client/{id}</code> Get client by id</summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Client
>    404 | ErrorResponse 
</details>
<details>
 <summary><code>PUT /client/{id}</code> Modify client</summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)
 
##### Request body

 *Scheme: ClientDto*
>  Field  | Type | Mandatory
>  ------ | --------- | -----
> username| String | yes
> password | String | yes

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Client
>    404 | ErrorResponse 
</details>
<details>
 <summary><code>DELETE /client/{id}</code> Delete client</summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)

##### Responses


>  Code | Scheme | Message
>  ------ | --------- | ---
>    200 | None | `Ride deleted successful`
>    404 | ErrorResponse | 
</details>

### Subscription

<details>
 <summary><code>GET /subscription</code> List subscriptions</summary>

##### Parameters
>  Param | Type 
>  ------ | ---------
>  ride_id| *(opitional)* Integer($int32)
>  client_id| *(opitional)* Integer($int32)

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Subscription
</details>

<details>
 <summary><code>GET /subscription/{id}</code> Get subscription by id</summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Subscription
>    404 | ErrorResponse 
</details>
<details>
 <summary><code>POST /subscription/{id}</code> Subscribe in ride</summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)
 
##### Request body

 *Scheme: SubscriptionDto*
>  Field  | Type | Mandatory
>  ------ | --------- | -----
> client_id| Integer($int32) | yes
> ride_id| Integer($int32) | yes

##### Responses

>  Code | Scheme 
>  ------ | ---------
>    200 | Subscription
>    404 | ErrorResponse 
</details>
<details>
 <summary><code>DELETE /subscription/{id}</code> Delete subscription</summary>
 
##### Parameters
>  Param | Type 
>  ------ | ---------
>  id| Integer($int32)

##### Responses


>  Code | Scheme | Message
>  ------ | --------- | ---
>    200 | None | `Inscription deleted successful`
>    404 | ErrorResponse | 
</details>

## Schemes

<details>
 <summary style="font-size: 16px"> ClientDto </summary>

>  Field  | Type 
>  ------ | --------- 
> username| String 
> password | String 

</details>
<details>
 <summary style="font-size: 16px"> Client </summary>

>  Field  | Type 
>  ------ | --------- 
> id| Integer($int32) 
> username| String 
> password | String
> rides | Ride
> subscriptions | Subscription
> role| Role

</details>
<details>
 <summary style="font-size: 16px"> RideDto </summary>

>  Field  | Type 
>  ------ | ---------
> name | String
> start_date| String($date-time) 
> start_date_registration| String($date-time)
> end_date_registration| String($date-time) 
> additional_information| String 
> start_place| String  
> participants_limit| Integer($int32) 
> client_id| Integer($int32) 

</details>
<details>
 <summary style="font-size: 16px"> Ride </summary>

>  Field  | Type 
>  ------ | ---------
> id| Integer($int32) 	
> name | String
> start_date| String($date-time) 
> start_date_registration| String($date-time)
> end_date_registration| String($date-time) 
> additional_information| String 
> start_place| String  
> participants_limit| Integer($int32) 
> client| Client

</details> 
<details>
 <summary style="font-size: 16px"> SubscriptionDto </summary>

>  Field  | Type 
>  ------ | --------- 
> client_id| Integer($int32)
> ride_id| Integer($int32) 

</details>
<details>
 <summary style="font-size: 16px"> Subscription </summary>

>  Field  | Type 
>  ------ | --------- 
> id| Integer($int32) 	
> client_id| Integer($int32)
> ride_id| Integer($int32) 
> subscription_date| String($date-time) 

</details>
<details>
 <summary style="font-size: 16px"> AuthenticationModel </summary>

>  Field  | Type 
>  ------ | --------- 
> username| String 
> password | String 

</details>
<details>
 <summary style="font-size: 16px"> ResponseModel </summary>

>  Field  | Type
>  ------ | ---------
>  token | String

</details>
<details>
 <summary style="font-size: 16px"> ErrorResponse </summary>

>  Field  | Type
>  ------ | ---------
>  error| String
>  message| String

</details>
