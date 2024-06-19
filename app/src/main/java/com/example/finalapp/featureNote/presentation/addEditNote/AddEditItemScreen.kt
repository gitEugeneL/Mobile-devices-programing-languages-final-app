package com.example.finalapp.featureNote.presentation.addEditNote

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalapp.R
import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.presentation.addEditNote.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditItemScreen(
    navController: NavController,
    itemColor: Int,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val nameState = viewModel.itemName.value
    val bodyState = viewModel.itemBody.value

    val scaffoldState = rememberScaffoldState()

    val itemBgAnimation = remember {
        Animatable(
            Color(if(itemColor != -1) itemColor else viewModel.itemColor.value)
        )
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {event ->
            when(event) {
                is AddEditItemViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                    )
                }
                is AddEditItemViewModel.UIEvent.SaveItem -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditItemEvent.SaveNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(R.string.save_note)
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(itemBgAnimation.value)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Item.itemColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .shadow(16.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.itemColor.value == colorInt) {
                                    Color.Black
                                } else {
                                    Color.Transparent
                                },
                                shape = CircleShape,
                            )
                            .clickable {
                                scope.launch {
                                    itemBgAnimation.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(480)
                                    )
                                }
                                viewModel.onEvent(AddEditItemEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            TransparentHintTextField(
                text = nameState.text,
                hint = nameState.hint,
                isHintVisible = nameState.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(AddEditItemEvent.ChangedName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditItemEvent.ChangeNameFocus(it))
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.size(12.dp))
            TransparentHintTextField(
                text = bodyState.text,
                hint = bodyState.hint,
                isHintVisible = bodyState.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(AddEditItemEvent.ChangedBody(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditItemEvent.ChangeBodyFocus(it))
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.h5
            )
        }
    }

}