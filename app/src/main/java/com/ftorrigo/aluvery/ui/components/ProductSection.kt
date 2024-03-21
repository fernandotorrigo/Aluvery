import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ftorrigo.aluvery.R
import com.ftorrigo.aluvery.model.Product
import java.math.BigDecimal

@Composable
fun ProductSection() {
    Column {
        Text(
            text = "Promoções",
            Modifier.padding(start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(
                    top = 8.dp
                )
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            ProductItem(
                Product(
                    name = "Hamburguer",
                    image = R.drawable.hamburguer,
                    price = BigDecimal("18.90")
                )
            )
            ProductItem(
                Product(
                    name = "Pizza",
                    image = R.drawable.pizza,
                    price = BigDecimal("34.90")
                )
            )
            ProductItem(
                Product(
                    name = "batata frita",
                    image = R.drawable.batata_frita,
                    price = BigDecimal("12.90")
                )
            )

            Spacer(Modifier)
        }
    }
}

@Preview
@Composable
private fun ProductSectionPreview() {
    ProductSection()
}