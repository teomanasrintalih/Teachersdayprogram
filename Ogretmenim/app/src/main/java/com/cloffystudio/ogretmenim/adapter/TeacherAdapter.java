package com.cloffystudio.ogretmenim.adapter;

import android.content.Context; // Uygulama bağlamını temsil eder
import android.view.LayoutInflater; // XML layout dosyalarını View objelerine dönüştürmek için kullanılır
import android.view.View; // Görünüm bileşenlerinin temel sınıfı
import android.view.ViewGroup; // Görünüm gruplarını temsil eder
import android.widget.TextView; // Metin göstermek için kullanılan View bileşeni

import androidx.annotation.NonNull; // Null olmayan parametre veya geri dönüş değerlerini belirtmek için
import androidx.recyclerview.widget.RecyclerView; // RecyclerView bileşeni

import com.cloffystudio.ogretmenim.R; // Kaynak dosyalarına erişim sağlar
import com.cloffystudio.ogretmenim.model.Teacher; // Öğretmen modelini temsil eder

import java.util.List; // Liste veri yapısı

// RecyclerView için Adapter: Öğretmen listesini görüntülemek için kullanılır
public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private final Context context; // Uygulamanın bağlamını temsil eder
    private final List<Teacher> teachers; // Öğretmenlerin listesi

    // Constructor: Adapter'ı oluştururken bağlamı ve öğretmen listesini alır
    public TeacherAdapter(Context context, List<Teacher> teachers) {
        this.context = context; // Bağlam atanır
        this.teachers = teachers; // Öğretmen listesi atanır
    }

    // ViewHolder oluşturulurken çağrılır. Her bir liste öğesi için görünüm oluşturur
    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // teacher_item.xml dosyasını inflate ederek bir View oluşturur
        View view = LayoutInflater.from(context).inflate(R.layout.teacher_item, parent, false);
        return new TeacherViewHolder(view); // Görünümü ViewHolder içine sararak döndürür
    }

    // Her bir liste öğesi için veri bağlama işlemini yapar
    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        // Pozisyona göre ilgili öğretmeni alır
        Teacher teacher = teachers.get(position);

        // Öğretmenin adını ve branşını ilgili TextView'lere atar
        holder.nameTextView.setText(teacher.getName());
        holder.branchTextView.setText(teacher.getBranch());
    }

    // Liste öğesi sayısını döndürür
    @Override
    public int getItemCount() {
        return teachers.size(); // Öğretmen listesinde kaç öğe varsa o kadar döndürür
    }

    // ViewHolder: RecyclerView'deki her bir liste öğesi için görünüm referanslarını tutar
    public static class TeacherViewHolder extends RecyclerView.ViewHolder {
        // teacher_item.xml'deki TextView bileşenlerini tanımlama
        TextView nameTextView, branchTextView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            // XML dosyasındaki öğretmen adı ve branşını temsil eden TextView'leri bağlar
            nameTextView = itemView.findViewById(R.id.teacherName);
            branchTextView = itemView.findViewById(R.id.teacherBranch);
        }
    }
}