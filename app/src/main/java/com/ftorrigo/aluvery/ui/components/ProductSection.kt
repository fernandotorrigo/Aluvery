import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.sampledata.sampleProducts
import com.ftorrigo.aluvery.model.Product
import com.ftorrigo.aluvery.ui.theme.AluveryTheme

@Composable
fun ProductSection(
    title: String, productsList: List<Product>
) {
    Column {
        Text(
            text = title,
            Modifier.padding(start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            Modifier
                .padding(
                    top = 8.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            )
        ) {

            items(productsList) { p ->
                ProductItem(product = p)

            }
        }
    }
}

@Preview
@Composable
private fun ProductSectionPreview() {
    AluveryTheme {
        Surface {
            ProductSection("Promoções", productsList = sampleProducts)
        }
    }
}

