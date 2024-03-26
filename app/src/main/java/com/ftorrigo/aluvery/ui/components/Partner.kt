package com.ftorrigo.aluvery.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ftorrigo.aluvery.R
import com.ftorrigo.aluvery.model.Shop

@Composable
fun Partner(
    shop: Shop,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier,
    ) {
        Column(
            Modifier
                .heightIn(150.dp, 200.dp)
                .width(100.dp)
        ) {
            val imageSize = 100.dp
            AsyncImage(
                model = shop.logo,
                contentDescription = null,
                Modifier
                    .size(imageSize)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_image)
            )
            Text(
                text = shop.name,
                fontSize = 16.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}