package com.example.progressify.service;

import com.example.progressify.model.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("subodh9165@gmail.com").password("Poonam@123").build()),
                Arguments.of(User.builder().username("disha.thakr@gmail.com").password("").build())
        );
    }
}
