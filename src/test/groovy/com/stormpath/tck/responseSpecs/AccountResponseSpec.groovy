/**
 * Created by edjiang on 3/30/16.
 */

package com.stormpath.tck.responseSpecs

import com.jayway.restassured.http.ContentType
import com.jayway.restassured.builder.ResponseSpecBuilder
import com.jayway.restassured.specification.ResponseSpecification
import com.stormpath.tck.util.*

import static org.hamcrest.Matchers.*

class AccountResponseSpec {
    static ResponseSpecification matchesAccount(TestAccount account) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
        builder
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .expectBody("size()", is(1))
            .expectBody("account.href", not(isEmptyOrNullString()))
            .expectBody("account.username", is(account.username))
            .expectBody("account.modifiedAt", not(isEmptyOrNullString()))
            .expectBody("account.status", equalToIgnoringCase("ENABLED"))
            .expectBody("account.createdAt", not(isEmptyOrNullString()))
            .expectBody("account.email", is(account.email))
            .expectBody("account.middleName", is(account.middleName))
            .expectBody("account.surname", is(account.surname))
            .expectBody("account.givenName", is(account.givenName))
            .expectBody("account.fullName", is("$account.givenName $account.middleName $account.surname".toString()))

        return builder.build()
    }

    static ResponseSpecification withoutLinkedResources() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
        builder.expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody("account.size()", is(10))
                .expectBody("account.emailVerificationToken", is(nullValue()))
                .expectBody("account.customData", is(nullValue()))
                .expectBody("account.providerData", is(nullValue()))
                .expectBody("account.directory", is(nullValue()))
                .expectBody("account.tenant", is(nullValue()))
                .expectBody("account.groups", is(nullValue()))
                .expectBody("account.groupMemberships", is(nullValue()))
                .expectBody("account.applications", is(nullValue()))
                .expectBody("account.apiKeys", is(nullValue()))
                .expectBody("account.accessTokens", is(nullValue()))
                .expectBody("account.refreshTokens", is(nullValue()))

        return builder.build()
    }
}
