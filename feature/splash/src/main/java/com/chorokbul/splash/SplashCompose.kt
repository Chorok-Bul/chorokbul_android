package com.chorokbul.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chorokbul.resource.R
import com.chorokbul.splash.common.DefaultMargin16
import com.chorokbul.splash.common.DefaultMargin24
import com.chorokbul.splash.common.EmptyColorBox

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.weight(1f))
        //임시 문구여서 Text로 우선 구현, 후에 벡터 svg로 교체 예정
        Text(
            modifier = modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = colorResource(id = R.color.green_major),
            text = "든든한 내 친구"
        )
        Image(
            painter = painterResource(id = R.drawable.img_chorokbul_name),
            contentDescription = null
        )
        Spacer(modifier = modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.img_chorokbul),
            contentDescription = null
        )
        Spacer(modifier = modifier.weight(1f))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_town),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        EmptyColorBox(height = DefaultMargin16, colorRes = R.color.green_mid)
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_load),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        EmptyColorBox(height = DefaultMargin24, colorRes = R.color.green_major)
    }
}

@Preview(name = "SplashCompose")
@Composable
private fun PreviewSplashCompose() {
    SplashScreen()
}
