package com.test.cookcook.ui.defaultCooked.addCooked;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.test.cookcook.MainActivity;
import com.test.cookcook.R;
import com.test.cookcook.data.entity.Cooked;
import com.test.cookcook.data.entity.Ingredients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCookedFragment extends Fragment {
    DatabaseReference mData;
    ImageButton add_img_cooked, add_add_ingre, add_add_steps;
    EditText add_name_cooked, add_intro_cooked, add_num;
    RecyclerView add_rc_ingre, add_rc_steps;
    Button add_btn_up, add_btn_save;
    ImageView add_img_food;
    Context mContext;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;
    public AddCookedFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public AddCookedFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_add_cooked, container, false);
        mData = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference =storage.getReference();
        //Find ID
        add_add_ingre = rootView.findViewById(R.id.add_add_ingre);
        add_add_steps = rootView.findViewById(R.id.add_add_steps);
        add_img_cooked = rootView.findViewById(R.id.add_img_cooked);
        add_name_cooked = rootView.findViewById(R.id.add_name_cooked);
        add_intro_cooked = rootView.findViewById(R.id.add_intro_cooked);
        add_num = rootView.findViewById(R.id.add_num);
        add_rc_ingre = rootView.findViewById(R.id.add_rc_ingre);
        add_rc_steps = rootView.findViewById(R.id.add_rc_steps);
        add_btn_save = rootView.findViewById(R.id.add_btn_save);
        add_btn_up = rootView.findViewById(R.id.add_btn_up);
        add_img_food = rootView.findViewById(R.id.add_img_food);
        ////

        add_img_cooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(rootView.getContext());
                dialog.setContentView(R.layout.dialog_image_cooked);
                dialog.setTitle("");

                Button btn1 = dialog.findViewById(R.id.add_dialog_btn1);//Lay anh tu bo nho cua may
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Log.e("Chon: ", " tai tu may");

                        chooseImage();
                    }
                });
                Button btn2 = dialog.findViewById(R.id.add_dialog_btn2);//Chup camera lay anh
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Log.e("Chon: ", " tu camera");

                        takeaphoto();
                    }
                });
                dialog.show();
            }
        });
        /////////////////////Post firebase


        add_btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cooked cooked = new Cooked(add_name_cooked.getText().toString(), add_intro_cooked.getText().toString(), Integer.parseInt(add_num.getText().toString()), "User", "Name random img");
                MainActivity mainActivity = (MainActivity) getActivity();
                String key = mainActivity.getMyData();

                Log.e("Key của món vừa tạo: ", key);// Cái này thay cho ID món để thêm bước làm + nguyên liệu
                updateCooked(cooked, key);
//                mData.child("Cooked").child(key).setValue(cooked, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                        if (databaseError == null) {
//                            Toast.makeText(mContext, "Đăng thành công", Toast.LENGTH_LONG).show();
//
//                        }else {
//                            Toast.makeText(mContext, "Đăng thất bại bị lỗi 1 cái gì đó -_-", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

            }
        });

        add_add_ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(rootView.getContext());
                dialog.setContentView(R.layout.dialog_ingre);
                dialog.setTitle("");

                Button add_dialog_ingre_ok = dialog.findViewById(R.id.add_dialog_ingre_ok);
                Button add_dialog_ingre_exit = dialog.findViewById(R.id.add_dialog_ingre_exit);

                add_dialog_ingre_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                add_dialog_ingre_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText add_dialog_content_ingre = dialog.findViewById(R.id.add_dialog_content_ingre);
                        EditText add_dialog_amount_ingre = dialog.findViewById(R.id.add_dialog_amount_ingre);
                        Spinner add_dialog_unit_ingre = dialog.findViewById(R.id.add_dialog_unit_ingre);

                        MainActivity mainActivity = (MainActivity) getActivity();
                        String key = mainActivity.getMyData();
                        Log.e("Key cooked ingre: ", key);
                        Ingredients ingredients = new Ingredients();
                        ingredients.setIdIngre(1);
                        ingredients.setName(add_dialog_content_ingre.getText().toString());
                        ingredients.setAmount(Integer.parseInt(add_dialog_amount_ingre.getText().toString()));
                        ingredients.setIdCooked(key);
                        ingredients.setUnit(add_dialog_unit_ingre.getSelectedItem().toString());
                        ingredients.setUnit(add_dialog_unit_ingre.getSelectedItem().toString());
                        mData.child("Ingredients").push().setValue(ingredients, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError == null) {
                                    Toast.makeText(mContext, "Đăng thành công", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(mContext, "Đăng thất bại bị lỗi 1 cái gì đó -_-", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                dialog.show();
            }
        });

        add_add_steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog= new Dialog(rootView.getContext());
                dialog.setContentView(R.layout.dialog_steps);
                Button add_dialog_steps_ok = dialog.findViewById(R.id.add_dialog_step_ok);
                Button add_dialog_steps_exit = dialog.findViewById(R.id.add_dialog_step_exit);
                ImageButton add_camera_img_steps=dialog.findViewById(R.id.add_camera_img_steps);
                ImageButton add_gallery_img_steps=dialog.findViewById(R.id.add_gallery_img_steps);
                TextView add_steps_image_flag= dialog.findViewById(R.id.add_steps_image_flag);
                // Nếu chọn ảnh xong thì thay bằng tên ảnh hoặc thay bằng "đã chọn, ok"

                add_camera_img_steps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takeaphoto_step();
                    }
                });
                add_gallery_img_steps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseImage_step();
                    }
                });
                add_dialog_steps_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                add_dialog_steps_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText add_dialog_num =dialog.findViewById(R.id.add_dialog_num);
                        EditText add_dialog_content=dialog.findViewById(R.id.add_dialog_content);
                        EditText add_dialog_time=dialog.findViewById(R.id.add_dialog_time);
                        Spinner add_dialog_unit=dialog.findViewById(R.id.add_dialog_unit);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return rootView;
    }

    private void takeaphoto() { //Từ camera
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhoto, 0);
    }

    private void chooseImage() { //Lấy từ máy
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    private void takeaphoto_step() {
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhoto, 2);
    }

    private void chooseImage_step() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Chuyển sang switch-case
        if (requestCode == 1) {
            Uri selectedImage = data.getData();
            //
            filePath=selectedImage;
            //
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(),selectedImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("Bitmap test: ", bitmap.toString());
            Log.e("URI Image from SDCARD: ", selectedImage.toString());
            add_img_food.setImageURI(selectedImage);//
        }
        if (requestCode == 0) {
            //
            filePath=data.getData();
            //
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Log.e("Bitmap Img from camera:", photo.toString());
            add_img_food.setImageBitmap(photo);
        }
        if (requestCode==2){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //Kết quả ảnh của Steps
        }
        if (requestCode==3){
            Uri selectedImage = data.getData();
            //Kết quả ảnh của Steps
        }
    }

    private void updateCooked(Cooked cooked, String key) {
        String imageName=UUID.randomUUID().toString();// Tên ảnh
        cooked.setImage(imageName);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, cooked);
        mData.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(mContext, "Đăng thành công", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(mContext, "Đăng thất bại bị lỗi 1 cái gì đó -_-", Toast.LENGTH_LONG).show();
                }
            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference ref= storageReference.child("Cooked/"+imageName );
        ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(mContext, "Uploaded", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, "Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded "+(int)progress+" %");
            }
        });
    }
}
