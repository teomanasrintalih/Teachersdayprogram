package com.cloffystudio.ogretmenim.adapter;

import android.content.Context; // Uygulama bağlamını temsil eder
import android.view.LayoutInflater; // XML dosyalarını View objelerine dönüştürmek için kullanılır
import android.view.View; // Görünüm bileşenlerinin temel sınıfı
import android.view.ViewGroup; // Görünüm gruplarını temsil eder
import android.widget.ImageView; // Görüntü göstermek için kullanılan View bileşeni

import androidx.annotation.NonNull; // Null olmayan parametreleri belirtmek için kullanılır
import androidx.recyclerview.widget.RecyclerView; // RecyclerView bileşeni

import com.cloffystudio.ogretmenim.R; // Kaynak dosyalarına erişim sağlar

import java.util.List; // Liste veri yapısını temsil eder

// RecyclerView için bir adapter: Görüntülerin yatay bir liste olarak gösterilmesini sağlar
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private final Context context; // Uygulama bağlamı
    private final List<Integer> imageList; // Görüntü kaynaklarının bulunduğu liste

    // Constructor: Adapter oluşturulurken bağlamı ve görüntü listesini alır
    public ImageAdapter(Context context, List<Integer> imageList) {
        this.context = context; // Uygulama bağlamı atanır
        this.imageList = imageList; // Görüntü listesi atanır
    }

    // Görünüm oluşturucu: Her bir liste öğesi için ViewHolder oluşturur
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item_image.xml dosyasını inflate ederek bir View oluşturur
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view); // Görünümü ViewHolder içine sararak döndürür
    }

    // Verilerin ilgili liste öğesine bağlanmasını sağlar
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        // Pozisyona göre ilgili görüntü kaynağını alır
        holder.imageView.setImageResource(imageList.get(position));
    }

    // Liste öğesi sayısını döndürür
    @Override
    public int getItemCount() {
        return imageList.size(); // Görüntü listesindeki toplam öğe sayısını döndürür
    }

    // ViewHolder: Her bir liste öğesi için View referanslarını tutar
    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView; // Görüntülerin gösterileceği ImageView bileşeni

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            // item_image.xml'deki ImageView bileşenini bağlar
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}