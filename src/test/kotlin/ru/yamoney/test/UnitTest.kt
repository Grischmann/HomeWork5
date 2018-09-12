package ru.yamoney.test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.comparesEqualTo
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class UnitTest {
        @Test
        fun checkDeposit(){
            val amount = BigDecimal("33.17")
            val depositOperation = Deposit("Peter small pig", amount)
            val initialBalance = BigDecimal("11.5")
            val newBalance = depositOperation.calculate(initialBalance)

            assertThat("deposit is deposit", newBalance, comparesEqualTo(initialBalance + amount))
        }

    }