package com.renamecompanyname.renameappname.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.renamecompanyname.renameappname.R
import com.renamecompanyname.renameappname.presentation.profile.ProfileViewModel
import com.renamecompanyname.renameappname.ui.common.CustomButton
import com.renamecompanyname.renameappname.ui.theme.RenameTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun ProfileScreen(
    uiState: ProfileViewModel.UiState.Success,
    onCreateUserClick: () -> Unit,
    onGetAllUsersClick: () -> Unit,
    onDeleteAllUsersClick: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    onFetchSomeDataClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (uiState.isLoadingUsers) {
            CircularProgressIndicator()
        } else {
            uiState.users.forEach {
                Text(
                    modifier = Modifier,
                    text = "name: ${it.name}, id: ${it.id}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            if (uiState.isFetchingData) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .shimmer()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .width(48.dp)
                            .background(color = MaterialTheme.colorScheme.outlineVariant),
                    )

                    Spacer(Modifier.size(8.dp))

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(color = MaterialTheme.colorScheme.outlineVariant),
                    )
                }
            } else {
                uiState.fetchedData.forEach {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier,
                            text = it.name,
                            style = MaterialTheme.typography.bodySmall,
                        )

                        Spacer(Modifier.size(8.dp))

                        AsyncImage(
                            model = it.flagUrl32,
                            contentDescription = it.name,
                            modifier = Modifier.size(48.dp),
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        CustomButton(text = "Create User", onClick = onCreateUserClick)

        if (uiState.isCreatingUser) {
            Spacer(modifier = Modifier.size(4.dp))
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.size(16.dp))

        CustomButton(text = "Get All Users", onClick = onGetAllUsersClick)

        Spacer(modifier = Modifier.size(16.dp))

        CustomButton(text = "Delete All Users", onClick = onDeleteAllUsersClick)

        if (uiState.isDeletingUsers) {
            Spacer(modifier = Modifier.size(4.dp))

            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.size(16.dp))

        CustomButton(text = "Fetch some data", onClick = onFetchSomeDataClick)

        Spacer(modifier = Modifier.size(56.dp))

        CustomButton(
            text = stringResource(id = R.string.navigate_to_edit_profile_btn_text),
            onClick = onNavigateToEditProfile,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        )
    }
}

@Composable
fun ProfileScreenFABButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        contentColor = MaterialTheme.colorScheme.background,
        containerColor = MaterialTheme.colorScheme.onBackground,
        onClick = onClick,
    ) {
        Text(text = stringResource(id = R.string.fab_text_navigate_to_home))
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    RenameTheme {
        Surface {
            Column(
                modifier =
                Modifier
                    .fillMaxSize(),
            ) {
                ProfileScreen(
                    uiState = ProfileViewModel.UiState.Success(users = emptyList()),
                    onCreateUserClick = { },
                    onGetAllUsersClick = { },
                    onDeleteAllUsersClick = { },
                    onNavigateToEditProfile = {},
                    onFetchSomeDataClick = {},
                )
            }
        }
    }
}
