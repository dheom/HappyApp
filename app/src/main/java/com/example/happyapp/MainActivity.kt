package com.example.happyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import coil.compose.rememberAsyncImagePainter
import com.example.happyapp.ui.theme.HappyAppTheme
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            HappyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {//Scaffold는 탑,푸터영역에 대한 기능 제공 -> 필수아님
        val context = LocalContext.current



        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "안녕하세요 행복 앱입니다")
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "이름") }
            )
            checkGridLayout()
            val data1 = "data1입니다" // 데이터를 보내기전 key-value형태로(그래야 꺼내쓰지)
            val data2 = 0 //데이터 없으면 기본 0
            intent = Intent(context, SecondActivity::class.java) //인텐트 생성
            intent.putExtra("data1", data1)
            intent.putExtra("data2", data2)



            Button(onClick = {
                startActivity(intent)
            }) {
                Text(text = "버튼")
            }
            Button(onClick ={}
            ) {

            }

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        HappyAppTheme {
            MainScreen()
        }
    }
}

@Composable
fun checkGridLayout() {
    val list = (1..8).map { it.toString() }
    LazyColumn {
        itemsIndexed((1..3).map { it.toString() }) { rowname, row ->
            LazyRow {
                itemsIndexed(list) { hi, column ->
                    Card(
                        colors = CardDefaults.cardColors(
                            Color.LightGray
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .height(100.dp)
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Row: $row\nCol: $column",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
//@Composable
//fun photoPick() { // 사진을 선택해서 어떻게 할건데?
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var context = LocalContext.current // 이게 잘 이해 안감
//    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
//        uri-> imageUri = uri
//        imageUri?.let {
//            try {
//                val image : InputImage = InputImage.fromFilePath(context,it)
//            }
//            catch (e:Exception){
//                e.printStackTrace()
//            }
//        }
//
//    }
//    if(imageUri == null){
//        Text(text = "사진을 선택해주세요")
//    }else{
//        Image(painter = rememberAsyncImagePainter(imageUri), contentDescription = null)
//    }
//}