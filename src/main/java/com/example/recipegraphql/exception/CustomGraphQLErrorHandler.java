package com.example.recipegraphql.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomGraphQLErrorHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected List<GraphQLError> resolveToMultipleErrors(Throwable ex, DataFetchingEnvironment env) {
        List<GraphQLError> graphQLErrors = new ArrayList<>();
        if(ex instanceof AppResourceNotFoundException){
            graphQLErrors.add(GraphqlErrorException.newErrorException()
                    .message(ex.getMessage())
                    .build()
            );
        }
        return graphQLErrors;
    }



}
